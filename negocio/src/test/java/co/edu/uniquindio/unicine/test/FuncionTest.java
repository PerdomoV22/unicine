package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import co.edu.uniquindio.unicine.repositorios.HorarioRepo;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepo;
import co.edu.uniquindio.unicine.repositorios.SalaRepo;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicioImpl;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

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

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void crearFuncion() throws Exception{

        Horario horario = horarioRepo.findById(1).orElse(null);
        Sala sala = salaRepo.findById(1).orElse(null);
        Pelicula pelicula = peliculaRepo.findById(1).orElse(null);
        Funcion funcion = new Funcion(4.500, sala, horario, pelicula);

        List<Funcion> funcs = funcionRepo.findAll();
        funcs.forEach(System.out::println);

        adminTeatroServicioImpl.crearFuncion(funcion);

        funcs = funcionRepo.findAll();
        funcs.forEach(System.out::println);
    }

    public boolean peliculaEnCartelera(String nombre){
        Pelicula pelicula = peliculaRepo.buscarPeliculaPorNombre(nombre);
        if (pelicula.getEstado()==true){
            return true;
        }else{
            return false;
        }
    }

     */
}
