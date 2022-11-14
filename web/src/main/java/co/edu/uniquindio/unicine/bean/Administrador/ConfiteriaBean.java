package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.TipoConfiteria;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class ConfiteriaBean {

    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private TipoConfiteria tipoConfiteria;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private List<Confiteria> confiteriasSeleccionados;

    private boolean editar;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private List<TipoConfiteria> tipoConfiterias;

    @PostConstruct
    public void init(){
        confiteria = new Confiteria();

        confiterias = administradorServicio.listarConfiteria();
        confiteriasSeleccionados = new ArrayList<>();
        editar= false;
        tipoConfiterias= Arrays.asList(TipoConfiteria.values());
    }

    public void registrarConfiteria(){

        try {
            if(!editar) {
                confiteria.setTipoConfiteria(TipoConfiteria.Bebidas);
                Confiteria registro = administradorServicio.crearConfiteria(confiteria);
                confiterias.add(registro);

                confiteria = new Confiteria();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
            }else{
                administradorServicio.actualizarConfiteria(confiteria);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
        }
    }



    public void eliminarConfiterias(){

        try {
            for (Confiteria confiteria : confiteriasSeleccionados){
                administradorServicio.eliminarConfiteria(confiteria.getCodigoProducto());
                confiterias.remove(confiteria);
            }
            confiteriasSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_confiteria", fm);
        }
    }


    public String getMensajeBorrar(){
        if(confiteriasSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + confiteriasSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CONFITERIA";
        }
        return "CREAR CONFITERIA" ;
    }


    public void seleccionarConfiteria(Confiteria confiteriaSelec){
        this.confiteria=confiteriaSelec;
        editar=true;
    }

    public void crearConfiteriaDialog(){
        this.confiteria= new Confiteria();
        editar=false;
    }
}
