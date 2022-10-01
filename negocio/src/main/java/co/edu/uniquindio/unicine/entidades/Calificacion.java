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

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer puntuacion;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Pelicula pelicula;

    public Calificacion(Integer puntuacion, Cliente cliente, Pelicula pelicula) {
        this.puntuacion = puntuacion;
        this.cliente = cliente;
        this.pelicula = pelicula;
    }
}
