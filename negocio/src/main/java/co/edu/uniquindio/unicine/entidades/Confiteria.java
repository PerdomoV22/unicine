package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Confiteria implements Serializable {

    //Esta es la PK de esta entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoProducto;

    //--------------------------- Atributos de la Entidad ---------------------------
    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConfiteria tipoConfiteria;

    //-------------------------------- Entidades Relacionadas -----------------------------------

    // Esta entidad se relaciona con la compraConfiteria, para encargarse de gestionar muchos a muchos y el cliente
    @ToString.Exclude
    @OneToMany(mappedBy = "confiteria")
    private List<CompraConfiteria> compraConfiterias;

    //Constructor
    public Confiteria(String nombre, Double precio, TipoConfiteria tipoConfiteria) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoConfiteria = tipoConfiteria;
    }

    public String getImagenPrincipal() {
        if(!imagenes.isEmpty()){
            String primero = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primero);
        }
        return "";
    }
}
