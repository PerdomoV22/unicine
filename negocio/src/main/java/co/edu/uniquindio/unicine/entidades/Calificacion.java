package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Calificacion implements Serializable {

    //Este atrubuto es la PK de la entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //------------------------- Atributos de la entidad -------------------------
    @Positive
    @Column(nullable = false)
    private Integer puntuacion;

    //-------------------------- Entidades Relacionadas ------------------------------------

    //Este entidad contiene un cliente, que es responsable o el titular de la calificacion
    @ManyToOne
    private Cliente cliente;

    //Este entidad contiene un pelicula, que es responsable o el titular de resibir la puntuacion de la calificacion
    @ManyToOne
    private Pelicula pelicula;

    //Constructor
    public Calificacion(Integer puntuacion, Cliente cliente, Pelicula pelicula) {
        this.puntuacion = puntuacion;
        this.cliente = cliente;
        this.pelicula = pelicula;
    }
}
