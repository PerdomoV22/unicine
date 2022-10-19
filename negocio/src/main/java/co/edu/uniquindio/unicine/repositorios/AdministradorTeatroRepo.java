package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, Integer> {

    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.contrasena = :contrasena")
    AdministradorTeatro comprobarAutenticacion(String correo, String contrasena);

    Optional<AdministradorTeatro> findByCedula(Integer cedula);

    Optional<AdministradorTeatro> findByCorreo(String correo);
}
