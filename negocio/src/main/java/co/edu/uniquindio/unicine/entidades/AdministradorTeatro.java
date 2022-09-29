package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    @ToString.Exclude
    @OneToOne(mappedBy = "administradorTeatro")
    private Teatro teatro;

    public AdministradorTeatro(Integer cedula, String nombre, String correo, String contrasena) {
        super(cedula, nombre, correo, contrasena);
    }
}
