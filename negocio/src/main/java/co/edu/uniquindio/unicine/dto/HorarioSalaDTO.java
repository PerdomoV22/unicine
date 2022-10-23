package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class HorarioSalaDTO {

    // Esta clase creada con la intencion de poner los atributos requeridos en la consulta del Horario y la Sala
    private Horario horario;
    private Sala sala;
}
