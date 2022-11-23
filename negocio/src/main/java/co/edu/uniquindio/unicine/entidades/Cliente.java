package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Cliente  extends Persona implements Serializable{

    // Esta entidad extiende de la entidad persona la cual contiene la PK

    //---------------------------------- Atributos de la Entidad -------------------------------
    @Column(nullable = false)
    private Boolean estado = false;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @ElementCollection
    private List<String> telefonos;

    //--------------------------------- Atributos Relacionados -----------------------------------

    // Esta entidad tiene una lista de compras, que seria todas las compras que el cliente realice
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    // Esta entidad tiene una lista de calificaciones, que seria las lista de calificaciones que genero el cliente a las peliculas
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Calificacion> calificaciones;

    //Este entidad tiene una lista de PQRS, que seria todas las quejas, reclamos, sugerencias realizadas
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Pqrs> pqrs;

    // Esta entidad tiene una la lista de cuponClientes, que guarda la PK de cliente para relacionar todos los cupones que tiene.
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponClientes;

    // Constructor
    @Builder
    public Cliente(Integer cedula, String nombre, String correo, String contrasena, List<String> telefonos, String imagen_perfil) {
        super(cedula, nombre, correo, contrasena);
        this.telefonos = telefonos;
        this.estado = false;
    }

    public String getImagenPrincipal() {
        if(!imagenes.isEmpty()){
            String primero = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primero);
        }
        return "";
    }
}
