package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
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
import java.util.List;

@Component
@ViewScoped
public class TeatroBean implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Ciudad> ciudades;
    @Getter @Setter
    private List<AdministradorTeatro> administradorTeatros;


    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;


    @PostConstruct
    public void init(){
        teatro = new Teatro();
        ciudades = administradorServicio.listarCiudad();
        administradorTeatros = administradorServicio.listarAdministradorTeatros();
    }


    public void registrarTeatro(){
        try {
            teatro.setAdministrador(null);
            adminTeatroServicio.crearTeatros(teatro);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_teatro", facesMessage);

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_teatro", facesMessage);
        }

    }
}
