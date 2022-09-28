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
public class Sala implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroSala;

    @Column(nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private DistribucionSillas distribucionSillas;

    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

    public Sala(Teatro teatro, DistribucionSillas distribucionSillas) {
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }
}
