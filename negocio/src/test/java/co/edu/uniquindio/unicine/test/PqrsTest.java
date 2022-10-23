package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Pqrs;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.repositorios.PqrsRepo;
import co.edu.uniquindio.unicine.servicios.ClienteServicioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PqrsTest {

    @Autowired
    private PqrsRepo pqrsRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ClienteServicioImpl clienteServicioImpl;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPqrsClientePorCedula(){
        List<Pqrs> pqrsList = pqrsRepo.obtenerPqrsClientePorCedula(1007);
        pqrsList.forEach(System.out::println);
    }

}
