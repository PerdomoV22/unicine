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
public class Ciudad implements Serializable {

    //Este atrubuto es la PK de la entidad
    @Id
    @EqualsAndHashCode.Include
    private Integer codigoPostal;

    //----------------------------------- Atribustos de la entidad -------------------------------------
    @Column(nullable = false)
    private String nombreCiudad;

    // --------------------------------- Entidades Relacionadad --------------------------------------

    //Esta entidad tiene una lista de teatros asociadas
    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List<Teatro> teatros;

    //Constructor
    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
}
