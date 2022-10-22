package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface ClienteServicio {

    //--------------------------------- LOGIN --------------------------------------
    Cliente login(String correo, String password) throws Exception;

    //--------------------------------- LOGIN --------------------------------------

    List<Pelicula> buscarPeliculaPorNombre (String nombre) throws Exception;

    List<Pelicula> buscarPeliculaPorGenero (Genero genero) throws Exception;

    //-------------------------------- CRUD DE CLIENTE ----------------------------
    Cliente registrarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorCedula(Integer cedula) throws Exception;
    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer codigoCliente) throws Exception;

    List<Cliente> listarClientes();

    //-------------------------------- LISTAR LAS PROPIAS COMPRAS ---------------------
    void listarHitorialCompra(Integer codigoCliente);

    //-------------------------------- REALIZAR COMPRA ---------------------------------
    Compra hacerCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception;

    //-------------------------------- REDIMIR CUPONES --------------------------------
    boolean redirCupon(Integer codigoCupon) throws Exception;

    //-------------------------------- Cambiar Contraseña ----------------------------
    boolean cambiarContraseña(String correo, String passwordNueva)throws Exception;


    Calificacion asignarCalificacion(Cliente cliente , Pelicula pelicula, Integer calificacion) throws Exception;

    Pqrs crearPqrs(Cliente cliente, Pqrs pqrs) throws Exception;

    void listarPqrs();
}
