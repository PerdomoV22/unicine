package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.aspectj.apache.bcel.generic.InstructionConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CuponCliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    private Cupon cupon;

    @ManyToOne
    private Cliente cliente;
}
