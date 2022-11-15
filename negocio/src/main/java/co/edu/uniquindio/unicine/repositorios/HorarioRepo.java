package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    // Esta consulta validad el horario dado el codigo
    @Override
    Optional<Horario> findById(Integer integer);

    // Esta consulta validad el horario dado la hora
    Optional<Horario> findByHora (LocalTime hora);
}
