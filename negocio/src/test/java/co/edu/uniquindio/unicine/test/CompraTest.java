package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradaCompras(){
        List<Entrada> listaEntradas = compraRepo.obtenerEntradas(1);
        Assertions.assertEquals(2, listaEntradas.size());
        listaEntradas.forEach(System.out::println);
    }
}
