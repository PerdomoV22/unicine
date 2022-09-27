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
public class Entrada implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Positive
    @Column(nullable = false)
    private Integer fila;

    @Positive
    @Column(nullable = false)
    private Integer columna;

    @ManyToOne
    private Compra compra;

    public Entrada(Double precio, Integer fila, Integer columna) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
    }
}
