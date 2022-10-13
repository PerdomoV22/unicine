package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;

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
    List<Compra> listarHitorialCompra(Integer codigoCliente) throws Exception;

    //-------------------------------- REALIZAR COMPRA ---------------------------------
    Compra hacerCompra(Compra compra) throws Exception;

    //-------------------------------- REDIMIR CUPONES --------------------------------
    boolean redirCupon(Integer codigoCupon) throws Exception;

    //-------------------------------- Cambiar Contraseña ----------------------------
    boolean cambiarContraseña(Integer cedela)throws Exception;
}
