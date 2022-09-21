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

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 12)
    private String nit;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    private Administrador administrador;

    @OneToMany(mappedBy = "teatro")
    private List<Sala> salas;

    @OneToMany(mappedBy = "teatro")
    private List<Compra> compras;

    @OneToOne(mappedBy = "teatro")
    private AdministradorTeatro administradorTeatro;

    public Teatro(String direccion, Ciudad ciudad, Administrador administrador) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.administrador = administrador;
    }
}
