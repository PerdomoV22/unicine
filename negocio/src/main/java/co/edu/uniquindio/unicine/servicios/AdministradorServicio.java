package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Teatro;

import java.util.List;

public interface AdministradorServicio {

    //------------------------------METODOS CRUD PARA LA CIUDAD---------------------------
    Ciudad crearCiudad(Ciudad ciudad) throws Exception;

    Ciudad actualizarCiudad(Ciudad ciudad) throws Exception;

    void eliminarCiudad(Integer codigo) throws Exception;

    List<Ciudad> listarCiudad() ;

    //-------------------------METODOS CRUD PARA LOS TEATROS--------------------------------
    Teatro crearTeatros(Teatro teatro) throws Exception;

    Teatro actualizarTeatros(Teatro teatro) throws Exception;

    void eliminarTeatros(Integer codigo) throws Exception;

    List<Teatro> listarTeatros() ;

    //------------------------METODOS CRUD PARA LOS CUPONES-----------------------------------
    Cupon crearCupones(Cupon cupon) throws Exception;

    Cupon actualizarCupones(Cupon cupon) throws Exception;

    void eliminarCupones(Integer codigo) throws Exception;

    List<Cupon> listarCupones() ;




}
