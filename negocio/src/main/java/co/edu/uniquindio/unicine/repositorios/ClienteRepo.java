package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    @Query("Select c from Cliente c where c.correo = :correo")
    Cliente buscarCliente(String correo);

    Cliente findByCorreo(String correo);

    @Query("select c from Cliente c where c.correo = :correo and c.contrasena = :contrasena")
    Cliente comprobarAutenticacion (String correo, String contrasena);

    Cliente findByCorreoAndContrasena(String email, String clave);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado (Boolean estado, Pageable paginador);

}

