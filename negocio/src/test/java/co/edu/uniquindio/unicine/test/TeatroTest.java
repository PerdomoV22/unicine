package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repositorios.TeatroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeatroTest {

    @Autowired
    private TeatroRepo teatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroCiudad(){
        List<Teatro> listaTeatrosCiudad = teatroRepo.listaTeatros("Santa Marta");
        Assertions.assertEquals(3, listaTeatrosCiudad.size());
        System.out.println(listaTeatrosCiudad);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void contarCiudad(){
        List<Object[]> listaCiudades = teatroRepo.contarCiudad();
        listaCiudades.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }
}
