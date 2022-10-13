package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    @Autowired
    private ClienteRepo clienteRepo;
    private PeliculaRepo peliculaRepo;
    private EmailServicio emailServicio;

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    //------------------------------------LOGIN----------------------------------------
    @Override
    public Cliente login(String correo, String password) throws Exception{
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if(cliente == null){
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }

        return cliente;
    }

    //----------------------------------- BUSCAR PELICULA -------------------------------
    @Override
    public List<Pelicula> buscarPeliculaPorNombre(String nombre) throws Exception {
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findByNombrePelicula(nombre);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }

        return (List<Pelicula>) peliculaGuardada.get();
    }

    @Override
    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorGenero(genero);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;
    }

    //---------------------------------- CRUD DE CLIENTE --------------------------------
    // Registrar --> No se con cuales parametros serian
    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception{

        boolean correoExiste = esRepetido(cliente.getCorreo());
        if(correoExiste){
            throw new Exception("El Correo ya esta en Uso");
        }

        emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombre()+" es un gusto que haya registrado en Unicine, para activar su cuenta ingrese en el siguiente link: url", cliente.getCorreo());
        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String correo){
        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }

    @Override
    public Cliente obtenerClientePorCedula(Integer cedula) throws Exception {

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedula);

        if(clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }

        return clienteGuardado.get();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }
        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer codigoCliente) throws Exception{

        Optional<Cliente> clienteGuardado = clienteRepo.findById(codigoCliente);

        if(clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    //------------------------------ LISTAR LA COMPRAS -------------------------------
    @Override
    public List<Compra> listarHitorialCompra(Integer codigoCliente) throws Exception{
        return null;
    }

    //---------------------------------- HACER UNA COMPRA --------------------------------
    @Override
    public Compra hacerCompra(Compra compra) throws Exception {
        return null;
    }

    //------------------------------------ REDMIR CUPON -----------------------------------
    @Override
    public boolean redirCupon(Integer codigoCupon) throws Exception{
        return false;
    }

    //------------------------------------- Cambiar Contraseña ------------------------------
    @Override
    public boolean cambiarContraseña(Integer cedela) throws Exception {
        return false;
    }
}
