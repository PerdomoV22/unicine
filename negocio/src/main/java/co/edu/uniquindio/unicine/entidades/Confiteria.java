package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Confiteria implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoProducto;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer unidades;

    @ManyToMany(mappedBy = "confiterias")
    private List<Compra> compras;

    public Confiteria(Double precio, Integer unidades) {
        this.precio = precio;
        this.unidades = unidades;
    }
}
