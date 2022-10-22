package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repositorios.ConfiteriaRepo;
import co.edu.uniquindio.unicine.repositorios.EntradaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConfiteriaTest {

    @Autowired
    private ConfiteriaRepo confiteriaRepo;

    @Autowired
    private EntradaRepo entradaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTotalConfitYEntradas(){
        Double valorTotal = 0.0;
        List<CompraConfiteria> compraConfiteria= confiteriaRepo.obtenerComprasConfit(2);

        for (CompraConfiteria cmConfi : compraConfiteria){
            valorTotal = valorTotal + cmConfi.getPrecio();
        }
        List<Entrada> entradas = entradaRepo.obtenerEntradasCompra(2);
        for (Entrada entrada : entradas){
            valorTotal = valorTotal + entrada.getPrecio();
        }
        System.out.println(valorTotal) ;
    }
}
