package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.dialect.identity.HANAIdentityColumnSupport;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {

    //Esta es la Pk de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //-------------------------------- Atributos de la Entidad --------------------------------
    @Positive
    @Column(nullable = false)
    private Double precio;

    // ------------------------------ Entidades Relacionadas -----------------------------------

    // Esta entidad se relaciona con la sala, para saber el esquema de la sala y el total de sillas de la sala
    @ManyToOne
    private Sala sala;

    // Esta entidad se relaciona con el horario, para saber los horarios disponibles y ocupados que se puedan seleccionar
    @ManyToOne
    private Horario horario;

    // Esta entidad se relaciona con pelicula para saber que pelicula esta creado y se pueda seleccionar
    @ManyToOne
    private Pelicula pelicula;

    //Esta entidad tiene una lista de compras
    @ToString.Exclude
    @OneToMany(mappedBy = "funcion")
    private List<Compra> compras;

    //Constructor
    @Builder
    public Funcion(Double precio, Sala sala, Horario horario, Pelicula pelicula) {
        this.precio = precio;
        this.sala = sala;
        this.horario = horario;
        this.pelicula = pelicula;
    }
}
