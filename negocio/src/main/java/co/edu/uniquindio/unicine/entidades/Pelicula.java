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

    //Esta es la pk de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    // ------------------------------------ Atributos de esta Entidad --------------------------------
    @Column(nullable = false, length = 50, unique = true)
    private String nombrePelicula;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false)
    private String imagen;

    @Lob
    @Column(nullable = false, length = 500)
    private String sinopsis;

    @Column(nullable = false)
    private String reparto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPelicula estadoPelicula;
    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false, length = 100)
    private List<Genero> genero;

    // ------------------------------------- Entidades Relacionadas ------------------------------------

    // Esta entidad tiene una lista de calificaciones generadas por el cliente
    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Calificacion> calificaciones;

    // Esta entidad tiene una lista de funciones que se relaciona con todo para la eleccion del cliente
    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    // Constructor
    public Pelicula(String nombrePelicula, String trailer, String imagen, String sinopsis, String reparto, EstadoPelicula estadoPelicula, List<Genero> genero) {
        this.nombrePelicula = nombrePelicula;
        this.trailer = trailer;
        this.imagen = imagen;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.estadoPelicula = estadoPelicula;
        this.genero = genero;
    }
}
