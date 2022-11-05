package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
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
import java.time.LocalDate;

@Component
@ViewScoped
public class CuponBean {

    @Getter @Setter
    private Cupon cupon;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        cupon = new Cupon();
    }

    public void registrarCupon(){

        try {
            administradorServicio.crearCupones(cupon);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
        }
    }
}
