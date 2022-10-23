package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer> {

    //Este consulta retorna una lista de todos los teatros dado el nombre de una ciudad
    @Query("select t from Teatro t where t.ciudad.nombreCiudad = :ciudad")
    List<Teatro> listaTeatros(String ciudad);

    //Esta consulta cuenta todos los teatos que hay en una ciudad y se muestra varios parametros
    @Query("select teatro.ciudad.codigoPostal, teatro.ciudad.nombreCiudad, count(teatro) from Teatro teatro group by teatro.ciudad")
    List<Object[]> contarCiudad();

    //Esta consulta validad si existe el teatro dado el nombre del teatro
    Optional<Teatro> findByNombre(String nombre);

}
