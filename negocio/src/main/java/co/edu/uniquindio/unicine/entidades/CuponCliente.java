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

    //Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //-------------------------------- Atributo de esta entidad ----------------------------------
    @Column(nullable = false)
    private Boolean estado;

    //-------------------------------- Entidades Relacionadad

    // Esta entidad se relacion con cupon para hacer mas facil el manejo con el cliente
    @ManyToOne
    private Cupon cupon;

    // Esta entidad esta realcionada con el cliente para saber los cupones que tiene
    @ManyToOne
    private Cliente cliente;

    //Constructor
    public CuponCliente(Boolean estado, Cupon cupon, Cliente cliente) {
        this.estado = estado;
        this.cupon = cupon;
        this.cliente = cliente;
    }
}
