package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {

    // Esta entidad extiende de la entidad persona, la cual contiene la llave primera que seria la cedula.

    //El administrador tiene un lista de teatros
    @ToString.Exclude
    @OneToMany(mappedBy = "administrador")
    private List<Teatro> teatros;


    //Constructor
    public Administrador(Integer cedula, String nombre, String correo, String contrasena) {
        super(cedula, nombre, correo, contrasena);
    }
}
