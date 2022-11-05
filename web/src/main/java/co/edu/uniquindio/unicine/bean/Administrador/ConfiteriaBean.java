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
import java.util.List;

@Component
@ViewScoped
public class ConfiteriaBean {

    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private TipoConfiteria tipoConfiteria;
    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        confiteria = new Confiteria();
    }

    public void registrarConfiteria(){

        try {
            confiteria.setTipoConfiteria(TipoConfiteria.Bebidas);
            administradorServicio.crearConfiteria(confiteria);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
        }
    }
}
