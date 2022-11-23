package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Setter @Getter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter  @Setter
    private List<PeliculaFuncion> peliculas;

    @Getter @Setter
    private List<Pelicula> peliculaSeleccionadas;

    @PostConstruct
    public void init(){
        peliculaSeleccionadas = new ArrayList<>();
        if(busquedaParam != null && !busquedaParam.isEmpty()){
            peliculas = clienteServicio.listarPeliculasFunciones(busquedaParam);
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }
}
