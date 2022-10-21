package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion, Integer> {

    @Query("select c from Calificacion c where c.cliente.cedula = :cedula")
    List<Calificacion> obtenerCalificacionesClientePorCedula(Integer cedula);

    @Query("select avg(c.puntuacion) from Calificacion c where c.pelicula.nombrePelicula = :nombre")
    Double obtenerPromedioCalificacionPelicula(String nombre);
}
