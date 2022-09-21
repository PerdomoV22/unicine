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

    @ManyToOne
    private Teatro teatro;

    @OneToMany(mappedBy = "sala")
    private List<SillaSala> sillas_sala;

    @ManyToOne
    private Pelicula pelicula;

    public Sala(Integer numeroSala, Teatro teatro, Pelicula pelicula) {
        this.numeroSala = numeroSala;
        this.teatro = teatro;
        this.pelicula = pelicula;
    }
}
