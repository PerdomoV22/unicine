package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@MappedSuperclass
@ToString
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer cedula; //La cedula no puede ser un campo autogenerado ya que el usuario debera escibirlo. En mi modelo yo no tengo cedula sino codigo

    @Column(nullable = false, length = 180)
    @Length(max = 180)
    private String nombre;

    @Column(nullable = false, unique = true, length = 200)
    @Email
    @Length(max = 200)
    private String correo;

    @ToString.Exclude
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    private String contrasena;

    public Persona(Integer cedula, String nombre, String correo, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
