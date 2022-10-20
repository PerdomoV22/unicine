package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Horario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    @ElementCollection
    private List<DiaSemana> dia;

    @Column(nullable = false)
    private Time hora;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFinal;

    @ToString.Exclude
    @OneToMany(mappedBy = "horario")
    private List<Funcion> funciones;

    public Horario(List<DiaSemana> dia, Time hora, LocalDate fechaInicio, LocalDate fechaFinal) {
        this.dia = dia;
        this.hora = hora;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }
}
