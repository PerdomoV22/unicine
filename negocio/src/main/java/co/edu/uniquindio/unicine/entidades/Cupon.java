package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cupon  implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Double valorDescuento;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false, length = 100)
    private String criterio;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @OneToOne(mappedBy = "cupon")
    private Compra compra;

    public Cupon(Double valorDescuento, LocalDate fechaVencimiento, String criterio, String descripcion) {
        this.valorDescuento = valorDescuento;
        this.fechaVencimiento = fechaVencimiento;
        this.criterio = criterio;
        this.descripcion = descripcion;
    }
}