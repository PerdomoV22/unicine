package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Calificacion;
import co.edu.uniquindio.unicine.repositorios.CalificacionRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CalificacionTest {

    @Autowired
    private CalificacionRepo calificacionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCalificacionesClientePorCedula(){
        List<Calificacion> calificaciones = calificacionRepo.obtenerCalificacionesClientePorCedula(1004);
        calificaciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPromedioCalificacionPelicula() {
        Double promedio = calificacionRepo.obtenerPromedioCalificacionPelicula("Fantasma");
        System.out.println(promedio);
    }
}
