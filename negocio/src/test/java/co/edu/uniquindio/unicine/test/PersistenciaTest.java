package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import co.edu.uniquindio.unicine.repositorios.TeatroRepro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersistenciaTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private FuncionRepo funcionRepo;

    @Autowired
    private TeatroRepro teatroRepro;

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){
        String[] telefonos = new String[] {"3117556502", "3148279730"};
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", Arrays.asList(telefonos), "url");
        Cliente clienteGuardado = clienteRepo.save(cliente);
        Assertions.assertEquals("Juan", clienteGuardado.getNombre());
    }

    @Test
    public void eliminar(){
        Cliente clienteBuscado = clienteRepo.findById(1004).orElse(null);
        clienteRepo.delete(clienteBuscado);
        Assertions.assertNull(clienteRepo.findById(1001).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){
        Cliente clienteGuardado = clienteRepo.findById(1004).orElse(null);
        clienteGuardado.setNombre("Jose");
        Cliente clienteNuevo = clienteRepo.save(clienteGuardado);
        Assertions.assertEquals("Jose", clienteNuevo.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){
        Optional<Cliente> clienteBuscado = clienteRepo.findById(1004);
        Assertions.assertNotNull(clienteBuscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Cliente> listaClientes = clienteRepo.findAll();
        System.out.println(listaClientes);
    }

    /*Consultas tipicas en MySQL*/
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerClienteCorreo(){
        Cliente cliente = clienteRepo.findByCorreo("perdomov.j07@gmail.com");
        Assertions.assertNotNull(cliente);
        System.out.println(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void compraborAutenticacion(){
        Cliente cliente = clienteRepo.findByCorreoAndContrasena("perdomov.j07@gmail.com", "0987");
        Assertions.assertNotNull(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador(){
        List<Cliente> listaClientes = clienteRepo.findAll(PageRequest.of(0,2)).toList();
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorEstado(){
        List<Cliente> listaClientes = clienteRepo.obtenerPorEstado(true,PageRequest.of(0,3));
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarPorNombre(){
        List<Cliente> listaClientes = clienteRepo.findAll(Sort.by("nombre"));
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarPorNombreDes(){
        List<Cliente> listaClientes = clienteRepo.findAll(Sort.by("nombre").descending());
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion(){
        String nombrePelicula = funcionRepo.obtenerNombrePelicula(5);
        Assertions.assertEquals("Fantasma", nombrePelicula);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroCiudad(){
        List<Teatro> listaTeatrosCiudad = teatroRepro.listaTeatros("Santa Marta");
        Assertions.assertEquals(3, listaTeatrosCiudad.size());
        System.out.println(listaTeatrosCiudad);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){
        List<Compra> listaCompras = clienteRepo.obtenerCompra("perdomov.j07@gmail.com");
        Assertions.assertEquals(3, listaCompras.size());
        listaCompras.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasOpcion2(){
        List<Compra> listaCompras = clienteRepo.obtenerCompraOpcion2("perdomov.j07@gmail.com");
        Assertions.assertEquals(1, listaCompras.size());
        listaCompras.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupones(){
        List<CuponCliente> listaCupones = clienteRepo.obtenerCupones("perdomov.j07@gmail.com");
        Assertions.assertEquals(2, listaCupones.size());
        listaCupones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradaCompras(){
        List<Entrada> listaEntradas = compraRepo.obtenerEntradas(1);
        Assertions.assertEquals(2, listaEntradas.size());
        listaEntradas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasTodo(){
        List<Object[]> listaCompras = clienteRepo.obtenerCompraTodos();
        listaCompras.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
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
}
