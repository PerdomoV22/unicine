package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;

public interface AdminTeatroServicio {

    Horario crearHorarios(Horario horario);

    Funcion crearFunciones(Funcion funcion);


}
