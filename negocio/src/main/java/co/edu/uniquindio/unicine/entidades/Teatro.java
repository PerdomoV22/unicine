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
public class Teatro implements Serializable {

    // Esta es la PK de esta Entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nit;

    //------------------------------- Atributos de esta Entidad ----------------------------------------
    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

    //------------------------------ Entidades Relacionadas ----------------------------------------------

    // Esta entidad tiene una ciudad
    @ManyToOne
    private Ciudad ciudad;

    // Esta entidad tiene un administrador que se encarga de gestionar lo nacional del teatro
    @ManyToOne
    private Administrador administrador;

    //Esta entidad tiene un lista de salas, para las funciones relacionadas
    @ToString.Exclude
    @OneToMany(mappedBy = "teatro")
    private List<Sala> salas;

    //Esta entidad tiene un administradorTeatro que se encarga de gestionar lo regional del teatro
    @OneToOne
    private AdministradorTeatro administradorTeatro;

    //Constructor
    public Teatro(String direccion, Ciudad ciudad, Administrador administrador) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.administrador = administrador;
    }
}
