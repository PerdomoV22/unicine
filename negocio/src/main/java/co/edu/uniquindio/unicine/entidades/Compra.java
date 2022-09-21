package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
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

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

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

    @OneToOne
    private Cupon cupon;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Confiteria> confiterias;

    @ManyToMany
    private List<SillaSala> sillaSalas;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private HorarioPelicula horarioPelicula;
}
