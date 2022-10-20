package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombrePelicula from Funcion f where f.codigo = :codigoFuncion")
    String obtenerNombrePelicula(Integer codigoFuncion);

    @Query("select distinct f.pelicula from Funcion f")
    List<Pelicula> obtenerPelicula();

    @Query("select new  co.edu.uniquindio.unicine.dto.FuncionDTO(f.pelicula.nombrePelicula, f.pelicula.estado, f.pelicula.imagen, f.sala.numeroSala, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombreCiudad, f.horario) from Funcion f where f.pelicula.codigo = :codigo")
    List<FuncionDTO> listarFunciones(Integer codigo);

    @Query("select funcion from Funcion funcion where funcion.sala.teatro.nit = :codigo and funcion.compras is empty ")
    List<Funcion> obtenerFuncionesSinCompra(Integer codigo);

    /*
    @Query("select f from Funcion f where f.sala.teatro.nit = :codigoTeatro and f.horario.fechaInicio < :fechafin or f.horario.fechaFinal > :fechaInicio")
    List<Funcion> obtenerFuncionesTeatro(Integer codigoTeatro, LocalDate fechaInicio, LocalDate fechaFin);*/

    //PENDIENTE---------------------
    @Query("select funcion.sala from Funcion funcion where funcion.sala.numeroSala = :codigoSala")
    Optional<Funcion> buscarSalaPorHorario(Integer codigoSala);

    @Query("select f from Funcion f where f.sala.numeroSala = :codigoSala and :dIaSemana in f.horario.dia and f.horario.hora = :hora")
    List<Funcion> obtenerFuncionesHorario(Integer codigoSala, DiaSemana dIaSemana, Time hora);
}
