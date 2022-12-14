package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Calificacion;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
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
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

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
        Cliente cliente = clienteRepo.findByCorreo("perdomov.j07@gmail.com").orElse(null);
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
        List<Cliente> listaClientes = clienteRepo.obtenerPorEstados(true,PageRequest.of(0,3));
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorEstado2(){
        Cliente cliente = clienteRepo.obtenerPorEstado(1004,true);
        System.out.println(cliente);
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
    public void obtenerCompras(){
        List<Compra> listaCompras = clienteRepo.obtenerCompra("perdomov.j07@gmail.com");
        Assertions.assertEquals(3, listaCompras.size());
        listaCompras.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasOpcion2(){
        List<Compra> listaCompras = clienteRepo.obtenerCompraOpcion3("perdomov.j07@gmail.com");
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
    public void obtenerComprasTodo(){
        List<Object[]> listaCompras = clienteRepo.obtenerCompraTodos();
        listaCompras.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }

    /**/
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCalificaciones(){
        List<Calificacion> listaCalificaciones = clienteRepo.obtenerCalificaciones(1004);
        listaCalificaciones.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasCliente(){
        List<Compra> compras= clienteRepo.obtenerComprasCliente(1005);
        compras.forEach(System.out::println);
    }

}
