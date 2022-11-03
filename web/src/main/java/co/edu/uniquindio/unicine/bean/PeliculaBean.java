package co.edu.uniquindio.unicine.bean;

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

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Setter @Getter
    private Pelicula pelicula;

    @Autowired
    private  AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
    }

    public void crearPelicula(){
       try {
           pelicula.setEstado(true);
           administradorServicio.crearPeliculas(pelicula);

           FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","La pelicula se ha creado exitosamente");
           FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
       }catch (Exception e){
           FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
       }
    }
}
