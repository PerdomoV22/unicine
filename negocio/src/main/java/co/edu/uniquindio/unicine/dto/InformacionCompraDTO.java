package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Funcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class InformacionCompraDTO {

    private Double precioTotal;
    private LocalDateTime fecha;
    private Funcion funcion;
    private Double precioEntradas;
    private Double precioConfiteria;
}
