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

    @ElementCollection
    private List<String> telefonos;

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

    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponClientes;

    public Cliente(Integer cedula, String nombre, String correo, String contrasena, List<String> telefonos, String imagen_perfil) {
        super(cedula, nombre, correo, contrasena);
        this.telefonos = telefonos;
        this.imagen_perfil = imagen_perfil;
        this.estado = false;
    }
}
