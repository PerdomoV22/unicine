package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repositorios.CalificacionRepo;
import co.edu.uniquindio.unicine.repositorios.CuponClienteRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponClienteTest {

    @Autowired
    private CuponClienteRepo cuponClienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesTodosClientes(){
        List<Object[]> cupones = cuponClienteRepo.obtenerCuponesTodosClientes();
        cupones.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }
}
