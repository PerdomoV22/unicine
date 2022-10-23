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

    // Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroSala;

    //----------------------------------- Atributos de esta entidad ---------------------------------
    @Column(nullable = false, length = 30)
    private String nombre;

    @ManyToOne
    private DistribucionSillas distribucionSillas;

    //------------------------------------ Entidad Relacionadas ------------------------------------

    // Esta entidad tiene un teatro
    @ManyToOne
    private Teatro teatro;

    //Esta entidad tiene una lista de funciones, que se determinara por los horarios agregados
    @ToString.Exclude
    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

    //Constructor
    public Sala(Teatro teatro, DistribucionSillas distribucionSillas) {
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }
}
