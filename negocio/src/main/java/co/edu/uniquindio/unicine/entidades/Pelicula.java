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
public class Pelicula implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String nombrePelicula;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false, length = 100)
    private String sinopsis;

    @Column(nullable = false)
    private String reparto;

     @Column(nullable = false)
    private String estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Genero genero;

    @OneToMany(mappedBy = "pelicula")
    private List<Sala> salas;

    @OneToMany(mappedBy = "pelicula")
    private List<Calificacion> calificaciones;

    @OneToMany(mappedBy = "pelicula")
    private List<HorarioPelicula> horarioPeliculas;
}
