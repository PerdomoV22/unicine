package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import co.edu.uniquindio.unicine.repositorios.HorarioRepo;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepo;
import co.edu.uniquindio.unicine.repositorios.SalaRepo;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Autowired
    private HorarioRepo horarioRepo;

    @Autowired
    private SalaRepo salaRepo;

    @Autowired
    private AdminTeatroServicioImpl adminTeatroServicioImpl;

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


    @Test
    @Sql("classpath:dataset.sql")
    public void ListaEntradas(){
        List<Entrada> listaEntradas = funcionRepo.listaEntradas(1);
        System.out.println(listaEntradas);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ListasCompras() {
        List<Compra> listaCompras = funcionRepo.listaCompra(1);
        System.out.println(listaCompras);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verificarSillas() {
        List<Entrada> listaEntradas = funcionRepo.verificarSillas(1, 3, 3);
        System.out.println(listaEntradas);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verificarSilla() {
        Entrada entrada = funcionRepo.verificarSilla(1, 3, 3);
        System.out.println(entrada);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncion() throws Exception{
        List<DiaSemana> dias = new ArrayList<>();
        dias.add(DiaSemana.LUNES);
        dias.add(DiaSemana.JUEVES);
        LocalTime hora = LocalTime.of(20, 30);
        List<Funcion> funciones = funcionRepo.obtenerFuncionesHorario(3, dias, hora);
        funciones.forEach(System.out::println);
    }

}
