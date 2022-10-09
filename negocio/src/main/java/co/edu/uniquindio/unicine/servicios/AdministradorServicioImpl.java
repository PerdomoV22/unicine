package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repositorios.AdministradorRepo;
import co.edu.uniquindio.unicine.repositorios.CiudadRepo;
import co.edu.uniquindio.unicine.repositorios.CuponRepo;
import co.edu.uniquindio.unicine.repositorios.TeatroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    @Autowired
    private AdministradorRepo administradorRepo;

    private CiudadRepo ciudadRepo;
    private TeatroRepo teatroRepo;
    private CuponRepo cuponRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {

        boolean ciudadExiste = ciudadRepetida(ciudad.getNombreCiudad());
        if(ciudadExiste){
            throw new Exception("La ciudad ya Existe");
        }
        return ciudadRepo.save(ciudad);
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

    @Override
    public Teatro crearTeatros(Teatro teatro) throws Exception {

        boolean teatroExiste = teatroRepetido(teatro.getNombre());
        if(teatroExiste){
            throw new Exception("El teatro ya Existe");
        }
        return teatroRepo.save(teatro);
    }

    public boolean teatroRepetido(String nombreTeatro){
        return teatroRepo.findByNombre(nombreTeatro).orElse(null) != null;
    }

    @Override
    public Teatro actualizarTeatros(Teatro teatro) throws Exception {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(teatro.getNit());
        if (teatroGuardado.isEmpty()){
            throw new Exception("El teatro NO EXISTE");
        }
        return teatroRepo.save(teatroGuardado.get());
    }

    @Override
    public void eliminarTeatros(Integer codigo) throws Exception {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(codigo);
        if (teatroGuardado.isEmpty()){
            throw new Exception("El teatro NO EXISTE");
        }
        teatroRepo.delete(teatroGuardado.get());

    }

    @Override
    public List<Teatro> listarTeatros()  {
        return teatroRepo.findAll()   ;
    }

    @Override
    public Cupon crearCupones(Cupon cupon) throws Exception {

        boolean cuponExiste = cuponRepetido(cupon.getCodigo());
        if(cuponExiste){
            throw new Exception("El cupon Ya Existe");
        }
        return cuponRepo.save(cupon);
    }

    public boolean cuponRepetido(Integer codigoCupon){
        return cuponRepo.findById(codigoCupon).orElse(null) != null;
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
