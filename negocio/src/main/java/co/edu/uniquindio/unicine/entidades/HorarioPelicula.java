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
public class HorarioPelicula implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Pelicula pelicula;

    @ManyToOne
    private Horario horario;

    @OneToMany(mappedBy = "horarioPelicula")
    private List<Compra> compras;

    public HorarioPelicula(Pelicula pelicula, Horario horario) {
        this.pelicula = pelicula;
        this.horario = horario;
    }
}
