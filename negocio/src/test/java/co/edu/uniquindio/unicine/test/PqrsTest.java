package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Pqrs;
import co.edu.uniquindio.unicine.repositorios.PqrsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PqrsTest {

    @Autowired
    private PqrsRepo pqrsRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPqrsClientePorCedula(){
        List<Pqrs> pqrsList = pqrsRepo.obtenerPqrsClientePorCedula(1007);
        pqrsList.forEach(System.out::println);
    }
}
