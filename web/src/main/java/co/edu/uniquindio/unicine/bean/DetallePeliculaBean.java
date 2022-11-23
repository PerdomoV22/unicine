package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.DiaSemana;
import co.edu.uniquindio.unicine.entidades.Calificacion;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private Cliente cliente;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Value("#{param['pelicula_id']}")
    private String codigoPelicula;
    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<LocalDate> fechas;

    @Getter @Setter
    private List<DiaSemana> diaSemanas;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private Calificacion calificacion;
    @Getter @Setter
    private Double promedioCalificacion;

    @Setter @Getter
    private String detallePelicula;

    @PostConstruct
    public void init(){
        try{
            ciudades = administradorServicio.listarCiudad();
            // teatros = clienteServicio.listarTeatroPeliculaDia(ciudad.getCodigoPostal(), pelicula.getCodigo(), fecha, diaSemana);
            if(codigoPelicula != null && !codigoPelicula.isEmpty()){
                pelicula = administradorServicio.obtenerPelicula(Integer.parseInt(codigoPelicula));
                promedioCalificacion = clienteServicio.promedioPelicula(pelicula);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void crearClasificacion(){

        try {
            calificacion.setPelicula(pelicula);
            calificacion.setCliente(cliente);
            clienteServicio.asignarCalificacion(calificacion);
            calificacion = new Calificacion();
            System.out.println(calificacion);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Calificacion Exitoso");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
        }

    }
}