package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PqrsRepo extends JpaRepository<Pqrs,Integer> {
}
