package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion(){
        String nombrePelicula = funcionRepo.obtenerNombrePelicula(5);
        Assertions.assertEquals("Fantasma", nombrePelicula);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculas(){
        List<Pelicula> listaPelicula = funcionRepo.obtenerPelicula();
        listaPelicula.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFunciones(){
        List<FuncionDTO> listaFunciones = funcionRepo.listarFunciones(1);
        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSinCompra(){
        List<Funcion> listaFuncion = funcionRepo.obtenerFuncionesSinCompra(1);
        System.out.println(listaFuncion);
    }

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesTeatro(){
        List<Funcion> listaFunciones = funcionRepo.obtenerFuncionesTeatro(1, "", "");
        System.out.println(listaFunciones);
    }*/



//PENDIENTE--------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void buscarSalaPorHorario(){
        Optional<Funcion> funcion = funcionRepo.buscarSalaPorHorario(1);
        System.out.println(funcion);
    }


}
