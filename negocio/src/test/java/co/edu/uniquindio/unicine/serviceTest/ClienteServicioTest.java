package co.edu.uniquindio.unicine.serviceTest;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    //---------------------------------  LOGUEARSE -------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void loguearse(){
        try {
            Cliente cliente = clienteServicio.login("perdomov.j07@gmail.com", "0987");
            Assertions.assertEquals("perdomov.j07@gmail.com", cliente.getCorreo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------- BUSCAR PELICULA ---------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaPorNombre (){
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaPorNombre("La huerfana: EL origen");
            peliculas.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------ CRUD DE CLIENTE ----------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest() {
        Cliente cliente = Cliente.builder().cedula(1234).nombre("Paco").correo("paco@gmail.com").contrasena("3321").imagen_perfil("fotopaco").build();
        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest() {

        try {
            Cliente cliente = clienteServicio.obtenerClientePorCedula(1007);
            cliente.setNombre("Nuevo nombre");
            Cliente nuevoCliente = clienteServicio.actualizarCliente(cliente);

            Assertions.assertEquals("Nuevo nombre", nuevoCliente.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest() {

        try {
            clienteServicio.eliminarCliente(1007);
            Cliente cliente = clienteServicio.obtenerClientePorCedula(1007);
            Assertions.assertNull(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClienteTest() {
        List<Cliente> listaClientes = clienteServicio.listarClientes();
        listaClientes.forEach(System.out::println);
    }

    //----------------------------------- LISTAR LAS COMPRAS DEL CLIENTE ------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasClienteTest() {
        try {
            clienteServicio.listarHitorialCompra(1004);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------- REALIZAR COMPRA ---------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompraTest() throws Exception {
        //Metodos obtenidos
        Cliente cliente = clienteServicio.obtenerClientePorCedula(1004);
        MedioPago medioPago = MedioPago.PSE;
        Cupon cupon = administradorServicio.obtenerCupones(1);
        Funcion funcion = adminTeatroServicio.obtenerFuncion(3);
        Confiteria confiteria = administradorServicio.obtenerConfiteria(1);
        Confiteria confiteria1 = administradorServicio.obtenerConfiteria(3);

        //Realizar
        List<Entrada> entradas = new ArrayList<>();
        Entrada entrada = new Entrada(4.500, 5, 5, null);
        Entrada entrada1= new Entrada(4.500, 6, 5, null);
        entradas.add(entrada);
        entradas.add(entrada1);

        List<CompraConfiteria> compraConfiterias = new ArrayList<>();
        CompraConfiteria compraConfiteria = new CompraConfiteria(1, null, confiteria);
        CompraConfiteria compraConfiteria1 = new CompraConfiteria(2, null, confiteria1);

        try {
            Compra compra = clienteServicio.hacerCompra(cliente, entradas, compraConfiterias, medioPago, cupon, funcion);
            Assertions.assertNotNull(compra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    @Sql("classpath:dataset.sql")
    public void redimirCuponTest() {
        try {
            boolean cuponRedimido = clienteServicio.redirCupon(3);
            Assertions.assertTrue(cuponRedimido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------CAMBIAR CONTRASEÑA ------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest() {
        try {
            Boolean cambiarContraseña = clienteServicio.cambiarContraseña("perdomov.j07@gmail.com", "2208");
            Assertions.assertTrue(cambiarContraseña);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //---------------------------------- METODOS DE CALIFICACION ---------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarCalificacionTest() {
        String[] telefonos = new String[] {"3117556502", "3148279730"};
        Cliente cliente = new Cliente(1004, "Juan", "perdomov.j07@gmail.com", "0987", Arrays.asList(telefonos), "url foto");
        //Pelicula pelicula = new Pelicula("La: EL origen", "url-video", "url-imagen", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan", EstadoPelicula.CARTELERA, null);
        Calificacion calificacion = new Calificacion(4, cliente, null);

        try {
            Calificacion guardarCalificacion = clienteServicio.asignarCalificacion(calificacion);
            Assertions.assertNotNull(guardarCalificacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void promedioPeliculaTest() {
        //Pelicula pelicula = new Pelicula("La: EL origen", "url-video", "url-imagen", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan", EstadoPelicula.CARTELERA, null);
        try {
            Double promedio = clienteServicio.promedioPelicula(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    //--------------------------------------- METODOS PQRS ------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearPqrsTest() {
        String[] telefonos = new String[] {"3117556502", "3148279730"};
        Cliente cliente = new Cliente(1004, "Juan", "perdomov.j07@gmail.com", "0987", Arrays.asList(telefonos), "url foto");
        Pqrs pqrs = new Pqrs("Error eleccion de medio de pago", "Elegi erroneamente el medio de pago en el proceso de la compra", cliente);
        try {
            Pqrs pqrsNueva = clienteServicio.crearPqrs(pqrs);
            Assertions.assertNotNull(pqrsNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPqrsTest() {
        try {
            clienteServicio.listarPqrs("perdomov.j07@gmail.com");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
