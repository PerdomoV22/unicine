package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer> {

    @Query("select p from Pelicula p where p.estado = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(Boolean estado);

    @Query("select p from Pelicula p where p.genero = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);


}
