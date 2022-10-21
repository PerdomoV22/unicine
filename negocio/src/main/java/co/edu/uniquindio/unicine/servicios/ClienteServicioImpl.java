package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    @Autowired
    private ClienteRepo clienteRepo;
    private PeliculaRepo peliculaRepo;
    private EmailServicio emailServicio;
    private CalificacionRepo calificacionRepo;
    private PqrsRepo pqrsRepo;
    private FuncionRepo funcionRepo;
    private CuponRepo cuponRepo;
    private CuponClienteRepo cuponClienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, EmailServicio emailServicio, CalificacionRepo calificacionRepo, PqrsRepo pqrsRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.emailServicio = emailServicio;
        this.calificacionRepo = calificacionRepo;
        this.pqrsRepo = pqrsRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
    }

    //------------------------------------LOGIN----------------------------------------
    @Override
    public Cliente login(String correo, String password) throws Exception{
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if(cliente == null){
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }
        //validar  estado del cliente
        cliente = clienteRepo.obtenerPorEstado(cliente.getCedula(), true);
        if(cliente == null){
            throw new Exception("No se activado la cuenta para ingresar");
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
    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception{

        boolean correoExiste = esRepetido(cliente.getCorreo());
        if(correoExiste){
            throw new Exception("El Correo ya esta en Uso");
        }
        //cedula
        boolean cedulaExiste = cedulaRepetida(cliente.getCedula());
        if(cedulaExiste){
            throw  new Exception("La cedula ingresada ya existe");
        }

        emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombre()+" es un gusto que haya registrado en Unicine, para activar su cuenta ingrese en el siguiente link: url", cliente.getCorreo());
        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String correo){
        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }

    private boolean cedulaRepetida(Integer cedula) {
        return clienteRepo.existsById(cedula);
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
    public void listarHitorialCompra(Integer codigoCliente){
        List<Compra> compras = clienteRepo.obtenerComprasCliente(codigoCliente);
        compras.forEach(System.out::println);
    }

    //---------------------------------- HACER UNA COMPRA --------------------------------
    @Override
    public Compra hacerCompra(Cliente cliente, Entrada entradas, CompraConfiteria compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception { // Cliente, Entradas, Canfiterias, medio de pago, cupon, funcion

        Compra compra = new Compra();
        compra.setFechaCompra(LocalDateTime.now());

        //verificar cliente,
        Optional<Cliente> clienteExiste = clienteRepo.findById(cliente.getCedula());
        if (clienteExiste.isEmpty()){
            throw new Exception("Cliente no existe");
        }
        //verificar que las sillas esten disponibles

        entradas = funcionRepo.verificarSilla(funcion.getCodigo(), entradas.getFila(), entradas.getColumna());
        if(entradas != null){
            throw new Exception("Las entredad seleccionadas ya estan ocupadas");
        }

        //redimir el cupon si no es null

        Optional<Cupon> cuponExiste = cuponRepo.findById(cupon.getCodigo());
        if (cuponExiste.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        boolean cuponCliente =false ;

        List<Cupon> cuponesCliente = cuponRepo.obtenerCuponesCliente(cliente.getCedula());
        for (Cupon cup : cuponesCliente){
            if (cupon.getCodigo() == cupon.getCodigo()){
                cuponCliente=true;
            }
        }
        if (!cuponCliente){
            throw new Exception("El cupon no es del cliente");
        }
        redirCupon(cupon.getCodigo());

        //sumar los precios, aplicar el descuento

        //persiste la compra


        return null;
    }

    //------------------------------------ REDMIR CUPON -----------------------------------
    @Override
    public boolean redirCupon(Integer codigoCupon) throws Exception{
        CuponCliente cuponGuardado = cuponClienteRepo.buscarCuponClientePorCodigoCupon(codigoCupon);
        if(cuponGuardado == null) {
            throw new Exception("El cupon no existe");
        }
        cuponGuardado.setEstado(true);
        cuponClienteRepo.save(cuponGuardado);
        return true;
    }

    //------------------------------------- Cambiar Contraseña ------------------------------
    public void enviarLinkRecuperacion(String correo){
        emailServicio.enviarEmail("Recuperacion password", "Para recupear la contraseña ingrese a: []", correo);
    }

    @Override
    public boolean cambiarContraseña(String correo, String passwordNueva ) throws Exception {

        Cliente cliente = clienteRepo.findByCorreo(correo).orElse(null);
        enviarLinkRecuperacion(correo);

        if(cliente==null){
            throw new Exception("El cliente no se encontro con el correo ingresado");
        }

        cliente.setContrasena(passwordNueva);
        clienteRepo.save(cliente);

        return true;
    }

    //---------------------------- METODOS DE CALIFICACION ----------------------------------------------------------
    @Override
    public Calificacion asignarCalificacion(Cliente cliente, Pelicula pelicula, Integer valorCalificacion) throws Exception {

        Pelicula peliculaExiste = peliculaRepo.buscarPeliculaPorNombre(pelicula.getNombrePelicula());

        if (peliculaExiste == null){
            throw new Exception("lA PELICULA NO SE ENCONTRO");
        }

        Calificacion calificacion = new Calificacion();
        calificacion.setPuntuacion(valorCalificacion);
        calificacion.setPelicula(pelicula);
        calificacion.setCliente(cliente);

        peliculaExiste.getCalificaciones().add(calificacion);

        return calificacionRepo.save(calificacion);
    }

    public Double promedioPelicula (Pelicula pelicula){
        Double promedio = calificacionRepo.obtenerPromedioCalificacionPelicula(pelicula.getNombrePelicula());
        return promedio;
    }

    //----------------------------- METODOS PQRS ---------------------------------------
    @Override
    public Pqrs crearPqrs(Cliente cliente, Pqrs pqrs) throws Exception{

        Optional<Cliente> clienteExiste= clienteRepo.findById(cliente.getCedula());
        if(clienteExiste.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        return pqrsRepo.save(pqrs);
    }

    @Override
    public void listarPqrs() {
        pqrsRepo.findAll();
    }
}
