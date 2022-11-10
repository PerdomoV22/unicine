package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Horario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FuncionDTO {

    // Esta clase creada con la intencion de poner los atributos requeridos en la consulta de la funcion
    private String nombrePelicula;
    private EstadoPelicula estadoPelicula;
    private String rutaImagen;
    private Integer numeroSala;
    private String direccionTeatro;
    private String nombreCiudad;
    private Horario horario;
}
