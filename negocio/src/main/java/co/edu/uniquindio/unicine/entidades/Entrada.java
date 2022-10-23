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

    //Esta es la Pk de esta Entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //-------------------------------- Atributos de la Entidad
    @Positive
    @Column(nullable = false)
    private Double precio;

    @Positive
    @Column(nullable = false)
    private Integer fila;

    @Positive
    @Column(nullable = false)
    private Integer columna;

    //------------------------- Entidades Relacionadas -------------------------------------

    //Esta entidad se relaciona con la compra, para saber el precion y la posicion de en la cual se va ubicar el cliente
    @ManyToOne
    private Compra compra;

    //Constructor
    public Entrada(Double precio, Integer fila, Integer columna, Compra compra) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
        this.compra = compra;
    }
}
