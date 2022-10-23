package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DistribucionSillas implements Serializable {

    // Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //------------------------------- Atributos de esta entidad ------------------------------------------
    @Column(nullable = false)
    private String esquema;

    @Positive
    @Column(nullable = false)
    private Integer totalSillas;

    @Positive
    @Column(nullable = false)
    private Integer filas;

    @Positive
    @Column(nullable = false)
    private Integer columnas;

    // --------------------------------------- Entidades Relacionadas --------------------------------

    //Esta entidad se relaciona con sala, para la distribucion correcta de las filas y columnas de cada sala
    @ToString.Exclude
    @OneToMany(mappedBy = "distribucionSillas")
    private List<Sala>  salas;

    //Constructor
    public DistribucionSillas(String esquema, Integer totalSillas, Integer filas, Integer columnas) {
        this.esquema = esquema;
        this.totalSillas = totalSillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}
