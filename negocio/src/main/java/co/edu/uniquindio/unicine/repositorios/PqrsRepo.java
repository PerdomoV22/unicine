package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepo extends JpaRepository<Pqrs,Integer> {

    //Esta consulta retorna las lista de pqrs que tiene un cliente dado la cedula del cliente
    @Query("select pq From Pqrs pq where pq.cliente.cedula = :cedula")
    List<Pqrs> obtenerPqrsClientePorCedula(Integer cedula);

}
