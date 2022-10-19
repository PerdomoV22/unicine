package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.*;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    @Query("Select c from Cliente c where c.correo = :correo")
    Cliente buscarCliente(String correo);

    Optional<Cliente> findByCorreo(String correo);

    @Query("select c from Cliente c where c.correo = :correo and c.contrasena = :contrasena")
    Cliente comprobarAutenticacion (String correo, String contrasena);

    Cliente findByCorreoAndContrasena(String email, String clave);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado (Boolean estado, Pageable paginador);

    @Query("select comp from Cliente cliente, in(cliente.compras) comp where cliente.correo = :correo")
    List<Compra> obtenerCompra (String correo);

    @Query("select c from Compra c where c.cliente.cedula = :cedula")
    List<Compra> obtenerComprasCliente (Integer cedula);

    @Query("select comp from Cliente cliente join cliente.compras comp where cliente.correo = :correo")
    List<Compra> obtenerCompraOpcion3(String correo);

    @Query("select cup from Cliente cliente join cliente.cuponClientes cup where cliente.correo = :correo")
    List<CuponCliente> obtenerCupones(String correo);

    @Query("select cliente.nombre, cliente.correo, comp from Cliente cliente left join cliente.compras comp")
    List<Object[]> obtenerCompraTodos();


    /*Dada la cedula de un cliente, retornar todas las calificaciones que ha generado*/
    @Query("select c from Cliente cliente join cliente.calificaciones c where cliente.cedula = :cedula")
    List<Calificacion> obtenerCalificaciones(Integer cedula);

}

