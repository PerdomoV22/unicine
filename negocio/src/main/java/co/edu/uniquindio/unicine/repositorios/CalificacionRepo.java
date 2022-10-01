package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Calificacion;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion, Integer> {

    @Query("select c from Calificacion c where c.cliente.cedula = :cedula")
    List<Calificacion> obtenerCalificacionesClientePorCedula(Integer cedula);
}
