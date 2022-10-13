package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminTeatroServicio {

    //------------------------------LOGIN-----------------------------------------------
    AdministradorTeatro login(String correo, String password) throws Exception;

    //-------------------------METODOS CRUD PARA LOS TEATROS--------------------------------
    Teatro crearTeatros(Teatro teatro) throws Exception;

    Teatro actualizarTeatros(Teatro teatro) throws Exception;

    void eliminarTeatros(Integer codigo) throws Exception;

    List<Teatro> listarTeatros();

    //------------------------------CRUD DE HORARIOS------------------------------------
    Horario crearHorarios(Horario horario)throws Exception;

    Horario obtenerHorario(Integer codigo)throws Exception;
    Horario actuaizarHorarios(Horario horario)throws Exception;

    void eliminarHorario(Integer codigo)throws Exception;

    List<Horario> listarHorarios();

    //----------------------------CRUD DE FUNCIONES------------------------------------------
    Funcion crearFunciones(Funcion funcion)throws Exception;
    Funcion obtenerFunciones(Integer codigo)throws Exception;
    Funcion actuaizarFunciones(Funcion funcion)throws Exception;

    void eliminarFuncion(Integer codigo)throws Exception;

    List<Funcion> listarFuncion();

    //------------------------------ CRUD DE SALAS ---------------------------------------------
    Sala crearSalas(Sala sala);
    Sala obtenerSala(Integer codigo)throws Exception;
    Sala actualizarSala(Sala sala)throws Exception;
    void eliminarSala(Integer codigoSala)throws Exception;
    List<Sala> listarSala();
}
