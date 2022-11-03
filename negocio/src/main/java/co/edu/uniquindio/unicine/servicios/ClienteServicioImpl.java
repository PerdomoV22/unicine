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

    //Construtor
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
    /**
     * @Param String correo, String password
     * @Return administrador
     * Este metodo se registra un cliente, el cual primero se validad su autenticacion
     */
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
        List<Pelicula> peliculaGuardada = peliculaRepo.findByNombrePelicula(nombre);

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

        Cliente clienteRegistrado =  clienteRepo.save(cliente);

        CuponCliente cupon = new CuponCliente();
        cupon.setCliente(clienteRegistrado);
        cupon.setCupon( cuponRepo.findById(4).get() );
        cupon.setEstado(false);
        cuponClienteRepo.save(cupon);

        //Generar un codigo para el cupon

        emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombre()+" es un gusto que haya registrado en Unicine, para activar su cuenta ingrese en el siguiente link: url", cliente.getCorreo());
        emailServicio.enviarEmail("Regalo Cupon Por Registro", "Hola "+cliente.getNombre()+" Haz adquirido un cupon por registrarte, el codigo es: "+cupon.getCodigo(), cliente.getCorreo());

        return clienteRegistrado;
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
    public void listarHitorialCompra(Integer cedulaCliente) throws Exception {
        Boolean cedulaExiste = clienteRepo.existsById(cedulaCliente);
        if(cedulaExiste == false){
            throw new Exception("La cedula del cliente ingresado no hiciste");
        }
        List<Compra> compras = clienteRepo.obtenerComprasCliente(cedulaCliente);
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

        //verificar que las sillas esten disponibles

        for (Entrada entrada : entradas ) {
           Entrada entradaOcupada = funcionRepo.verificarSilla(funcion.getCodigo(), entrada.getFila(), entrada.getColumna());
            if (entradaOcupada == null) {
                throw new Exception("Las entredas seleccionadas ya estan ocupadas");
            }
        }
        //redimir el cupon si no es null

        if(cupon != null) {

            Optional<CuponCliente> cuponExiste = cuponClienteRepo.findById(cupon.getCodigo());
            if (cuponExiste.isEmpty()) {
                throw new Exception("El cupon no existe");
            }

        }

            boolean cuponCliente = false;

            List<CuponCliente> cuponesCliente = clienteRepo.obtenerCuponesPorCedula(cliente.getCedula());
            for (CuponCliente cup : cuponesCliente) {
                if (cup.getCupon().getCodigo() == cupon.getCodigo()) {
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

        //persiste la compra
        compra.setValorTotal(valorTotalConDescuento);
        compra.setFechaCompra(LocalDateTime.now());
        compra.setMedioPago(medioPago);
        compra.setNumeroBoletas(entradas.size());
        compraRepo.save(compra);

        //Mandar compra a todas las entrdas y las conprasConfiterias que lleagn
        for (Entrada entrada : entradas) {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        }

        for (CompraConfiteria compConfi : compraConfiteria){
            compConfi.setCompra(compra);
            compraConfiteriaRepo.save(compConfi);
        }
       
        //Mandar cupon primera compra
        List<Compra> compras = clienteRepo.obtenerComprasCliente(cliente.getCedula());

        if (compras.size() == 1) {
            emailServicio.enviarEmail("Primera compra", "Hola " + cliente.getNombre() + " Por tu primera compra, haz adquirido un cupon " , cliente.getCorreo());
        }

        return compra;
    }

    public Double calcularValorTotal(List<Entrada> entradas,List<CompraConfiteria> compraConfiteria ){

        Double valorTotal = 0.0;

        for (CompraConfiteria cmConfi : compraConfiteria){
            valorTotal = valorTotal + cmConfi.getPrecio() * cmConfi.getUnidades();
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
    //no usar ñs
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
    public Calificacion asignarCalificacion(Calificacion calificacion) throws Exception {

        //Validar si el cliente existe
        boolean clienteExiste = cedulaRepetida(calificacion.getCliente().getCedula());
        if(!clienteExiste){
            throw new Exception("El cliente con la cedula ingresada no existe");
        }
        //Validar la pelicula
        Pelicula peliculaExiste = peliculaRepo.buscarPeliculaPorNombre(calificacion.getPelicula().getNombrePelicula());
        if (peliculaExiste == null){
            throw new Exception("lA PELICULA NO SE ENCONTRO por el nombre ingresado");
        }

        //Se le actucalizando el valor de la calificacion a la pelicula
        peliculaExiste.getCalificaciones().add(calificacion);
        peliculaRepo.save(peliculaExiste);

        return calificacionRepo.save(calificacion);
    }

    public Double promedioPelicula (Pelicula pelicula){
        Double promedio = calificacionRepo.obtenerPromedioCalificacionPelicula(pelicula.getNombrePelicula());
        return promedio;
    }

    //----------------------------- METODOS PQRS ---------------------------------------
    @Override
    public Pqrs crearPqrs(Pqrs pqrs) throws Exception{

        Optional<Cliente> clienteExiste= clienteRepo.findById(pqrs.getCliente().getCedula());
        if(clienteExiste.isEmpty()){
            throw new Exception("El cliente no existe con la cedula ingresada");
        }
        return pqrsRepo.save(pqrs);
    }

    @Override
    public void listarPqrs(String correo)throws Exception {
        Optional<Cliente> correoExisteCliente = clienteRepo.findByCorreo(correo);
        if(correoExisteCliente.isEmpty()){
            throw new Exception("El correo ingresado no existe");
        }
        List<Pqrs> pqrs = clienteRepo.obtenerPqrs(correo);
        pqrs.forEach(System.out::println);
    }
}
