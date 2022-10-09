package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad,Integer> {

    @Query("select ciudad.codigoPostal, ciudad.nombreCiudad from Ciudad ciudad join ciudad.teatros tea group by ciudad.codigoPostal")
        List<Object[]> contarTeatros();

    Optional<Ciudad> findByNombreCiudad(String nombre);

}
