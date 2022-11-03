package co.edu.uniquindio.unicine.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Setter @Getter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    public void init(){

    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "/resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        return "";
    }
}