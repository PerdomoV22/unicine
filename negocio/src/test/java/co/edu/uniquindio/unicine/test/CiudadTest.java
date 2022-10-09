package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repositorios.CiudadRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;
    @Test
    @Sql("classpath:dataset.sql")
    public void contarTeatros(){
        List<Object[]> listaTeatros = ciudadRepo.contarTeatros();
        listaTeatros.forEach(System.out::println);
    }
}
