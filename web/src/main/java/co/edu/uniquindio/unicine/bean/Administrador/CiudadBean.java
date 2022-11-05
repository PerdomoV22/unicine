package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class CiudadBean {

    @Getter @Setter
    private Ciudad ciudad;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        ciudad = new Ciudad();
    }

    public void registrarCiudad(){

        try {
                administradorServicio.crearCiudad(ciudad);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_ciudad", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_ciudad", facesMessage);
        }
    }
}
