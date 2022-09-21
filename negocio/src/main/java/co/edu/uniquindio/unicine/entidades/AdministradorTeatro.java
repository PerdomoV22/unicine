package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AdministradorTeatro implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String usuario;

    @Column(nullable = false)
    private String contrasena;

    @OneToOne
    private Teatro teatro;
}
