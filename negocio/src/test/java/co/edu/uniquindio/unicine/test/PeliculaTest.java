package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorEstado(){
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorEstado(false);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorGenero(){
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorGenero(Genero.TERROR);
        peliculas.forEach(System.out::println);
    }

}
