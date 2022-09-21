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
public class Silla implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroSilla;

    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "silla")
    private List<SillaSala> sillaSalas;

    public Silla(String estado) {
        this.estado = estado;
    }
}
