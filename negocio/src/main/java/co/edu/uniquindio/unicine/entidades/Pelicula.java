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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String nombrePelicula;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false, length = 500)
    private String sinopsis;

    @Column(nullable = false)
    private String reparto;

     @Column(nullable = false)
    private Boolean estado;

    @ElementCollection
    @Column(nullable = false, length = 10)
    private List<Genero> generos;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Calificacion> calificaciones;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    public Pelicula(String nombrePelicula, String trailer, String imagen, String sinopsis, String reparto, Boolean estado, List<Genero> generos) {
        this.nombrePelicula = nombrePelicula;
        this.trailer = trailer;
        this.imagen = imagen;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.estado = estado;
        this.generos = generos;
    }
}
