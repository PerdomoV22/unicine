package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Cliente  extends Persona implements Serializable{

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false)
    private String imagen_perfil;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Calificacion> calificaciones;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Pqrs> pqrs;

    public Cliente(Integer cedula, String nombre, String correo, String contrasena, String telefono, String imagen_perfil) {
        super(cedula, nombre, correo, contrasena);
        this.telefono = telefono;
        this.imagen_perfil = imagen_perfil;
        this.estado = false;
    }
}
