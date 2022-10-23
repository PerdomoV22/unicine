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
public class CompraConfiteria implements Serializable {

    // Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //---------------------------- Atributos de la entidad ------------------------
    @Positive
    @Column(nullable = false)
    private Double precio;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    // ---------------------------- Atributos relacionados -------------------------

    //Esta entidad tiene una compra, la cual se relaciona con el codigo de la compra que seria la FK
    @ManyToOne
    private Compra compra;

    //Esta entidad tiene una confiteria, la cual se relaciona con el codigo de la confiteria que seria la FK
    @ManyToOne
    private Confiteria confiteria;

    //Constructor
    public CompraConfiteria(Integer unidades, Compra compra, Confiteria confiteria) {
        this.precio = confiteria.getPrecio()*unidades;
        this.unidades = unidades;
        this.compra = compra;
        this.confiteria = confiteria;
    }
}
