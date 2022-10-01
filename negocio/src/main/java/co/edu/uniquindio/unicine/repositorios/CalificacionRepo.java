package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion, Integer> {
}
