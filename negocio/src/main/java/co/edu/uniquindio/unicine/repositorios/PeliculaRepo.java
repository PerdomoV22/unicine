package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.Calificacion;
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

    @Query("select p from Pelicula p where p.generos = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);

    @Query("select pelicula from Pelicula pelicula where pelicula.nombrePelicula like concat('%', :nombre, '%') and pelicula.estado = :estado")
    List<Pelicula> buscarPelicula(String nombre, Boolean estado);

    @Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDTO(funcion.horario, funcion.sala) from Pelicula pelicula join pelicula.funciones funcion where pelicula.codigo = :codigoPelicula and funcion.sala.teatro.nit = :codigoTeatro")
    List<HorarioSalaDTO> listarHorario(Integer codigoPelicula, Integer codigoTeatro);

    @Query("select pelicula from Pelicula pelicula where :genero member of pelicula.generos order by pelicula.nombrePelicula asc")
    List<Pelicula> listarPeliculas(Genero genero);

}
