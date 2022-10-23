package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pqrs implements Serializable {

    // Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoPqrs;

    //-------------------------------- Atributos de la Entidad ------------------------------------
    @Column(nullable = false, length = 300)
    private String asunto;

    @Column(nullable = false, length = 300)
    private String descripcion;

    // ---------------------------- Entidades de la relacion --------------------------------------

    // Esta entidad se relacion con el cliente, por el cliente es que hace la PQRS
    @ManyToOne
    private Cliente cliente;

    //Constructor
    public Pqrs(String asunto, String descripcion, Cliente cliente) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }
}
