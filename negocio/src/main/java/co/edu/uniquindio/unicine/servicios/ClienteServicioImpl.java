package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    private ConfiteriaRepo confiteriaRepo;
    private CompraRepo compraRepo;
    private EntradaRepo entradaRepo;

    private CompraConfiteriaRepo compraConfiteriaRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, EmailServicio emailServicio, CalificacionRepo calificacionRepo, PqrsRepo pqrsRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.emailServicio = emailServicio;
        this.calificacionRepo = calificacionRepo;
        this.pqrsRepo = pqrsRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
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

        emailServicio.enviarEmail("Regalo Cupon Por Registro", "Hola "+cliente.getNombre()+" Haz adquirido un cupon por registrarte", cliente.getCorreo());

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
    public Compra hacerCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception { // Cliente, Entradas, Canfiterias, medio de pago, cupon, funcion

        Compra compra = new Compra();
        
        //verificar cliente,
        Optional<Cliente> clienteExiste = clienteRepo.findById(cliente.getCedula());
        if (clienteExiste.isEmpty()){
            throw new Exception("Cliente no existe");
        }

        Integer numeroCompras = clienteRepo.obtenerCantidadComprasCliente(cliente.getCedula());
        //verificar que las sillas esten disponibles

        for (Entrada entrada : entradas ) {
           Entrada entradaOcupada = funcionRepo.verificarSilla(funcion.getCodigo(), entrada.getFila(), entrada.getColumna());
            if (entradas != null) {
                throw new Exception("Las entredad seleccionadas ya estan ocupadas");
            }
        }
        //redimir el cupon si no es null

            Optional<Cupon> cuponExiste = cuponRepo.findById(cupon.getCodigo());
            if (cuponExiste.isEmpty()) {
                throw new Exception("El cupon no existe");
            }
            boolean cuponCliente = false;

            List<Cupon> cuponesCliente = cuponRepo.obtenerCuponesCliente(cliente.getCedula());
            for (Cupon cup : cuponesCliente) {
                if (cupon.getCodigo() == cupon.getCodigo()) {
                    cuponCliente = true;
                }
            }
            if (!cuponCliente) {
                throw new Exception("El cupon no es del cliente");
            }
            redirCupon(cupon.getCodigo());

        //sumar los precios, aplicar el descuento

        Double valorTotal = calcularValorTotal(entradas, compraConfiteria);
        Double valorTotalConDescuento = calcularValorTotalConDescuento (valorTotal, cupon.getValorDescuento());

        compra.setEntradas(entradas);
        compra.setCompraConfiterias(compraConfiteria);
        compra.setValorTotal(valorTotalConDescuento);

        for (Entrada entrada : entradas) {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        }

        for (CompraConfiteria compConfi : compraConfiteria){
            compConfi.setCompra(compra);
            compraConfiteriaRepo.save(compConfi);
        }
        //persiste la compra
        if (numeroCompras == 0) {
            Cupon cuponPrimeraCompra = new Cupon ();
            emailServicio.enviarEmail("Primera compra", "Hola " + cliente.getNombre() + " Por tu primera compra, haz adquirido un cupon " , cliente.getCorreo());
        }
        compra.setFechaCompra(LocalDateTime.now());
        return compraRepo.save(compra);
    }

    public Double calcularValorTotal(List<Entrada> entradas,List<CompraConfiteria> compraConfiteria ){

        Double valorTotal = 0.0;

        for (CompraConfiteria cmConfi : compraConfiteria){
            valorTotal = valorTotal + cmConfi.getPrecio();
        }
        for (Entrada entrada : entradas){
            valorTotal = valorTotal + entrada.getPrecio();
        }
        System.out.println(valorTotal) ;
        return valorTotal ;
    }

    public Double calcularValorTotalConDescuento(Double valorTotal, Double descuento ){

        Double valorConDescuento = valorTotal-(valorTotal*descuento);
        return valorConDescuento ;
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
