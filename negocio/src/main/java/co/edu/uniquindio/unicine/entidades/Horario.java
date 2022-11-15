package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Horario implements Serializable {

    // Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    // --------------------------- Atributos de esta Entidad
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ElementCollection
    private List<DiaSemana> dia;

    @Column(nullable = true)
    private LocalTime hora;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFinal;

    //------------------------------------ Entidades Relacionadad ------------------------------------

    // Esta entidad tiene una lista de funciones, para relacionar los horarios disponibles y ocupados
    @ToString.Exclude
    @OneToMany(mappedBy = "horario")
    private List<Funcion> funciones;

    // Constructor
    public Horario(List<DiaSemana> dia, LocalTime hora, LocalDate fechaInicio, LocalDate fechaFinal) {
        this.dia = dia;
        this.hora = hora;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }
}
