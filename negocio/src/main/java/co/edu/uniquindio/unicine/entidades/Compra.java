package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    //La Pk de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //----------------------------------- Atributos de la Entidad ------------------------------
    @Column(nullable = false)
    private Integer numeroBoletas;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MedioPago medioPago;

    @Positive
    @Column(nullable = false)
    private Double valorTotal;

    //------------------------------- Atributos Relacionados -------------------------------------

    // Esta entidad tiene un cupon, que se puede redimir si lo tiene el cliente
    @OneToOne
    private Cupon cupon;

    // Esta entidad tiene un cliente, que valida los datos de la compra
    @ManyToOne
    private Cliente cliente;

    // Esta entidad tiene una lista de compraConfiteria, que se encarga de toda la confiteria elegida por el cliente
    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> compraConfiterias;

    // Esta entidad tiene funcion, la cual esta diseñada para la organizacion del teatro
    @ManyToOne
    private Funcion funcion;

    // Esta entidad tiene una lista de entredad, la cual validad por el cliente, todas las entradas que el selecciona
    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;

    //Constructor
}
