package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepo extends JpaRepository<Entrada,Integer> {

    @Query("select comp.entradas from Compra comp where comp.codigo = :codigo")
    List<Entrada> obtenerEntradasCompra(Integer codigo);
}
