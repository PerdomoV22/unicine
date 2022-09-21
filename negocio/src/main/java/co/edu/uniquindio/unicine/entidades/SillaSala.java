package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class SillaSala implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Silla silla;

    @ManyToMany(mappedBy = "sillaSalas")
    private List<Compra> compras;

    public SillaSala(Double precio, Sala sala, Silla silla) {
        this.precio = precio;
        this.sala = sala;
        this.silla = silla;
    }
}
