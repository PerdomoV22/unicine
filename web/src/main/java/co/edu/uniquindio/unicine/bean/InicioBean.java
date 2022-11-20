package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Getter @Setter
    private List<Pelicula> peliculasCartelera;
    @Getter @Setter
    private List<Pelicula> peliculasProximamente;
    @Getter @Setter
    private List<Ciudad> ciudades;
    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<String> listaImagenes;


    @PostConstruct
    public void inicializar(){
        try {
            listaImagenes = new ArrayList<>();
            listaImagenes.add("https://images.hdqwalls.com/wallpapers/black-adam-2021-movie-4z.jpg");
            listaImagenes.add("https://lumiere-a.akamaihd.net/v1/images/557bd062deb3b4e8264c6985480bfa68_4096x1861_82baf17c.jpeg?region=0,0,4096,1861");
            listaImagenes.add("https://i.ytimg.com/vi/bDm1uikmADg/maxresdefault.jpg");

            peliculasCartelera = clienteServicio.buscarPeliculaPorEstado(EstadoPelicula.CARTELERA);
            peliculasProximamente = clienteServicio.buscarPeliculaPorEstado(EstadoPelicula.PROXIMAMENTE);
            ciudades = administradorServicio.listarCiudad();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void elegirCiudad(){
        try {
            if(ciudad != null){
                peliculasCartelera = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigoPostal(), EstadoPelicula.CARTELERA);
                peliculasProximamente = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigoPostal(), EstadoPelicula.PROXIMAMENTE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
