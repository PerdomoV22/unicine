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

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoPostal;

    @Column(nullable = false)
    private String nombreCiudad;

    @OneToMany(mappedBy = "ciudad")
    private List<Teatro> teatros;

    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
}
