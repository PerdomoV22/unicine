package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    //Este comentrio es pa probar que sirva
    
    @Autowired
    private final AdministradorRepo administradorRepo;

    private final CiudadRepo ciudadRepo;
    private final TeatroRepo teatroRepo;
    private final CuponRepo cuponRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final AdministradorTeatroRepo administradorTeatroRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo, CiudadRepo ciudadRepo, TeatroRepo teatroRepo, CuponRepo cuponRepo, PeliculaRepo peliculaRepo, ConfiteriaRepo confiteriaRepo, AdministradorTeatroRepo administradorTeatroRepo) {
        this.administradorRepo = administradorRepo;
        this.ciudadRepo = ciudadRepo;
        this.teatroRepo = teatroRepo;
        this.cuponRepo = cuponRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.administradorTeatroRepo = administradorTeatroRepo;
    }

    //------------------------------------ LOGIN ----------------------------------------------
    @Override
    public Administrador login(String correo, String password) throws Exception {
        Administrador administrador = administradorRepo.compraboarAuntenticacion(correo, password);

        if(administrador == null){
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }
        return administrador;
    }

    //-------------------------------- CRUD DE CIUDAD ---------------------------------
    @Override
    public Ciudad crearCiudad(Ciudad ciudad){
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigo);
        if(ciudad.isEmpty()){
            throw new Exception("No existe la ciudad con ese codigo postal");
        }
        return ciudad.get();
    }

    public boolean ciudadRepetida(String nombreCiudad){
        return ciudadRepo.findByNombreCiudad(nombreCiudad).orElse(null)!= null;
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(ciudad.getCodigoPostal());
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer codigo) throws Exception {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(codigo);
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        ciudadRepo.delete(ciudadGuardado.get());
    }

    @Override
    public List<Ciudad> listarCiudad()  {
        return ciudadRepo.findAll();
    }

    //------------------------------ CRUD DE ADMINISTRADORTEATROS --------------------------------------------
    @Override
    public AdministradorTeatro crearAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception {

        boolean administradorTeatrosExiste = AdministradorRepetido(administradorTeatro.getNombre());
        if(administradorTeatrosExiste){
            throw new Exception("El administrador ya Existe");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public AdministradorTeatro obtenerAdministrador(Integer codigo) throws Exception {
        Optional<AdministradorTeatro> administradorTeatro = administradorTeatroRepo.findById(codigo);
        if(administradorTeatro.isEmpty()){
            throw new Exception("No existe un administrador de teatros con ese codigo");
        }
        return administradorTeatro.get();
    }

    private boolean AdministradorRepetido(String nombre){
        return administradorTeatroRepo.findByNombre(nombre).orElse(null)!=null;
    }
    @Override
    public AdministradorTeatro actualizarAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findById(administradorTeatro.getCedula());
        if(administradorGuardado.isEmpty()){
            throw new Exception("El administrador NO EXISTE");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministradorTeatros(Integer cedula) throws Exception {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findById(cedula);
        if(administradorGuardado.isEmpty()){
            throw new Exception("El administrador NO EXISTE");
        }
        administradorTeatroRepo.delete(administradorGuardado.get());
    }

    @Override
    public List<AdministradorTeatro> listarAdministradorTeatros() {
        return administradorTeatroRepo.findAll();
    }

    //----------------------------------------- CRUD DE PELICULAS
    @Override
    public Pelicula crearPeliculas(Pelicula pelicula){
        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigo) throws Exception {
        Optional<Pelicula> pelicula = peliculaRepo.findById(codigo);
        if(pelicula.isEmpty()){
            throw new Exception("No existe la pelicula con ese codigo");
        }
        return pelicula.get();
    }

    @Override
    public Pelicula actualizarPeliculas(Pelicula pelicula) throws Exception {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(pelicula.getCodigo());
        if (peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }
        return peliculaRepo.save(peliculaGuardada.get());
    }

    @Override
    public void eliminarPeliculas(Integer codigo) throws Exception {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(codigo);
        if (peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }
        peliculaRepo.delete(peliculaGuardada.get());
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
    }

    //---------------------------------- CRUD DE CONFITERIA -------------------------------------
    @Override
    public Confiteria crearConfiteria(Confiteria confiteria){
        return confiteriaRepo.save(confiteria);
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigo) throws Exception {
        Optional<Confiteria> confiteria = confiteriaRepo.findById(codigo);
        if(confiteria.isEmpty()){
            throw new Exception("No existe la confiteria con ese codigo");
        }
        return confiteria.get();
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(confiteria.getCodigoProducto());
        if (confiteriaGuardada.isEmpty()){
            throw new Exception("El producto NO EXISTE");
        }
        return confiteriaRepo.save(confiteriaGuardada.get());
    }

    @Override
    public void eliminarConfiteria(Integer codigo) throws Exception {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(codigo);
        if (confiteriaGuardada.isEmpty()){
            throw new Exception("El producto NO EXISTE");
        }
        confiteriaRepo.delete(confiteriaGuardada.get());
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return confiteriaRepo.findAll();
    }


    //------------------------------- CRUD DE CUPONES ------------------------------------
    @Override
    public Cupon crearCupones(Cupon cupon){
        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon obtenerCupones(Integer codigo) throws Exception {
        Optional<Cupon> cupon = cuponRepo.findById(codigo);
        if(cupon.isEmpty()){
            throw new Exception("No existe el cupon con ese codigo postal");
        }
        return cupon.get();
    }

    @Override
    public Cupon actualizarCupones(Cupon cupon) throws Exception {

        Optional<Cupon> cuponGuardado = cuponRepo.findById(cupon.getCodigo());
        if(cuponGuardado.isEmpty()){
            throw new Exception("El cupon NO EXISTE");
        }
        return cuponRepo.save(cuponGuardado.get());
    }

    @Override
    public void eliminarCupones(Integer codigo) throws Exception {
        Optional<Cupon> cuponGuardado = cuponRepo.findById(codigo);
        if(cuponGuardado.isEmpty()){
            throw new Exception("El cupon NO EXISTE");
        }
        cuponRepo.delete(cuponGuardado.get());
    }

    @Override
    public List<Cupon> listarCupones() {
        return cuponRepo.findAll();
    }
}
