package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Setter @Getter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Pelicula> peliculas;

    @Setter @Getter
    private List<Pelicula> peliculasSeleccionados;

    private boolean editar;

    @Autowired
    private  AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        pelicula = new Pelicula();

        peliculas = administradorServicio.listarPeliculas();
        peliculasSeleccionados = new ArrayList<>();
        editar= false;
    }

    public void registrarPelicula(){
       try {
           if(!editar) {
               pelicula.setEstadoPelicula(EstadoPelicula.CARTELERA);
               Pelicula registro = administradorServicio.crearPeliculas(pelicula);
               peliculas.add(registro);

               pelicula = new Pelicula();
               FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha creado exitosamente");
               FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
           }else{
               administradorServicio.actualizarPeliculas(pelicula);
               FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha actualizado exitosamente");
               FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
           }
       }catch (Exception e){
           FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
       }
    }



    public void eliminarPeliculas(){

        try {
            for (Pelicula pelicula : peliculasSeleccionados){
                administradorServicio.eliminarPeliculas(pelicula.getCodigo());
                peliculas.remove(pelicula);
            }
            peliculasSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_pelicula", fm);
        }
    }


    public String getMensajeBorrar(){
        if(peliculasSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + peliculasSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR PELICULA";
        }
        return "CREAR PELICULA" ;
    }


    public void seleccionarPelicula(Pelicula peliculaSelec){
        this.pelicula=peliculaSelec;
        editar=true;
    }

    public void crearPeliculaDialog(){
        this.pelicula= new Pelicula();
        editar=false;
    }
}
