package co.edu.uniquindio.unicine.bean.Cliente;

import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Component
@ViewScoped
public class ClienteBean implements Serializable {

    //Variables del Bean
    @Setter @Getter
    private Cliente cliente;

    @Setter @Getter
    private String confirmacionPassword;

    @Autowired
    private ClienteServicio clienteServicio;

    private Map<String, String> imagenes;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    //-----------------------------Metodos para verificar el funcionamiento de la pagina-----------------------

    /**
     * Se instancia el cliente, para que no vaya generar la exception de null
     */
    @PostConstruct
    public void instanciarCliente (){
        cliente = new Cliente();
    }


    public void registrarCliente(){
        try {
            if(!imagenes.isEmpty()){
                if (cliente.getContrasena().equals(confirmacionPassword)) {
                    clienteServicio.registrarCliente(cliente);

                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
                } else {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Contraseñas no coinciden");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
                }
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
        }

    }

    public void subirImagenes(FileUploadEvent event) throws IOException {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedfile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            e.printStackTrace();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_pelicula", fm);
        }
    }

    private File convertirUploadedfile(UploadedFile imagen) throws IOException{
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }
}
