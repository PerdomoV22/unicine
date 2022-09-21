package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Horario implements Serializable {

    @Id
    private Time horaInicio;

    @Column(nullable = false)
    private Time horaFinal;

    @OneToMany(mappedBy = "horario")
    private List<HorarioPelicula> horarioPeliculas;

    public Horario(Time horaInicio, Time horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
}
