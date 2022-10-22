package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repositorios.CuponRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    public CuponTest(CuponRepo cuponRepo) {
        this.cuponRepo = cuponRepo;
    }


}
