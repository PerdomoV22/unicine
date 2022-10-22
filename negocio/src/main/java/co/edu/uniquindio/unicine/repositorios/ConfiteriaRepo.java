package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria,Integer> {

    Optional<Confiteria> findByNombre(String nombreConfiteria);

    @Query("select cp from CompraConfiteria  cp where cp.compra.codigo = :codigo")
    List<CompraConfiteria> obtenerComprasConfit(Integer codigo);
}
