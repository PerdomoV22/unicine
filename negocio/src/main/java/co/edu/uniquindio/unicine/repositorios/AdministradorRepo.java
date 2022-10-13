package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, Integer> {

    @Query("select a from Administrador a where a.correo = :correo and a.contrasena = :contrasena")
    Administrador compraboarAuntenticacion(String correo, String contrasena);
}
