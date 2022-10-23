package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, Integer> {

    //Esta consulta valida la autenticacion del cliente en el login al momento de ingresar
    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.contrasena = :contrasena")
    AdministradorTeatro comprobarAutenticacion(String correo, String contrasena);

    //Esta consulta validad si existe un administradorTeatro ingresando la cedula
    Optional<AdministradorTeatro> findByCedula(Integer cedula);

    //Esta consulta validad si existe un administradorTeatro ingresando el correo
    Optional<AdministradorTeatro> findByCorreo(String correo);
}
