package co.edu.uniquindio.unicine.serviceTest;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.AdministradorRepo;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;


    //-----------------------------------ADMINISTRADOR-------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest() throws Exception {

        try {
            Administrador administrador = administradorServicio.login("lopezjose@gmail.com","9876");
            Assertions.assertEquals("lopezjose@gmail.com" , administrador.getCorreo());
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    //-----------------------------------CIUDAD--------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCiudadTest(){
        try {
            Ciudad ciudad = new Ciudad("Pereira");
            Ciudad ciudadCreada = administradorServicio.crearCiudad(ciudad);
            Assertions.assertNotNull(ciudadCreada);
            System.out.println(ciudadCreada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest() throws Exception {
        try {
            Ciudad ciudadCreada = administradorServicio.obtenerCiudad(3);
            Assertions.assertNotNull(ciudadCreada);
            System.out.println(ciudadCreada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest() throws Exception {

        try {
            Ciudad ciudadGuardada = administradorServicio.obtenerCiudad(4);
            System.out.println(ciudadGuardada.getNombreCiudad());
            ciudadGuardada.setNombreCiudad("Guajira");
            Ciudad ciudadActualizada = administradorServicio.actualizarCiudad(ciudadGuardada);
            Assertions.assertNotNull(ciudadActualizada);
            System.out.println(ciudadActualizada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest() throws Exception {
        try {
            administradorServicio.eliminarCiudad(4);
            Ciudad ciudad = administradorServicio.obtenerCiudad(4);
            Assertions.assertNull(ciudad);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudad()  {
       List<Ciudad> ciudades = administradorServicio.listarCiudad();
       ciudades.forEach(System.out::println);
    }


    //-----------------------------------ADMINISTRADOR_TEATRO------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdministradorTeatrosTest() throws Exception {

        try {
            AdministradorTeatro administradorTeatro = new AdministradorTeatro(1234,"felipe","felipe@gmail.com","0991");
            AdministradorTeatro adminCreado= administradorServicio.crearAdministradorTeatros(administradorTeatro);
            Assertions.assertNotNull(adminCreado);
            System.out.println(adminCreado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorTest() throws Exception {
        try {
            AdministradorTeatro administradorTeatro = administradorServicio.obtenerAdministrador(1015);
            Assertions.assertNotNull(administradorTeatro);
            System.out.println(administradorTeatro.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorTeatrosTest() throws Exception {

        try {
            AdministradorTeatro adminTeatroGuardado = administradorServicio.obtenerAdministrador(1015);
            System.out.println(adminTeatroGuardado.getNombre());
            adminTeatroGuardado.setNombre("lady");
            AdministradorTeatro adminTeatroActualizado= administradorServicio.actualizarAdministradorTeatros(adminTeatroGuardado);
            Assertions.assertNotNull(adminTeatroActualizado);
            System.out.println(adminTeatroActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTeatrosTest() throws Exception {

        try {
            administradorServicio.eliminarAdministradorTeatros(1015);
            AdministradorTeatro administradorTeatro = administradorServicio.obtenerAdministrador(1015);
            Assertions.assertNull(administradorTeatro);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradorTeatros() {
        List<AdministradorTeatro> adminisTeatros = administradorServicio.listarAdministradorTeatros();
        adminisTeatros.forEach(System.out::println);
    }

    //-----------------------------------------PELICULAS--------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPeliculas(){
        try {
            Pelicula pelicula = new Pelicula("La purga 2", "trailer de la peli" , " sipnisis peli" , "reparto de la peli", EstadoPelicula.CARTELERA, null);
            Pelicula peliculaCreada= administradorServicio.crearPeliculas(pelicula);
            Assertions.assertNotNull(peliculaCreada);
            System.out.println(peliculaCreada.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaTest() throws Exception {

        try {
            Pelicula pelicula = administradorServicio.obtenerPelicula(5);
            Assertions.assertNotNull(pelicula);
            System.out.println(pelicula.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPeliculasTest() throws Exception {

        try {
            Pelicula peliculaGuardado = administradorServicio.obtenerPelicula(5);
            System.out.println(peliculaGuardado.getNombrePelicula());
            peliculaGuardado.setNombrePelicula("son como ninos");
            Pelicula peliculaActualizada= administradorServicio.actualizarPeliculas(peliculaGuardado);
            Assertions.assertNotNull(peliculaActualizada);
            System.out.println(peliculaActualizada.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPeliculasTest() throws Exception {

        try {
            administradorServicio.eliminarPeliculas(5);
            Pelicula pelicula= administradorServicio.obtenerPelicula(5);
            Assertions.assertNull(pelicula);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas() {
        List<Pelicula> peliculas = administradorServicio.listarPeliculas();
        peliculas.forEach(System.out::println);
    }


    //----------------------------------CONFITERIA---------------------------------


    @Test
    @Sql("classpath:dataset.sql")
    public void crearConfiteria(){

        try {
            Confiteria confiteria = new Confiteria( "ICE" , 7500.00 ,  TipoConfiteria.Bebidas);
            Confiteria ConfiteriaCreada= administradorServicio.crearConfiteria(confiteria);
            Assertions.assertNotNull(ConfiteriaCreada);
            System.out.println(ConfiteriaCreada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerConfiteriaTest() throws Exception {

        try {
            Confiteria confiteria = administradorServicio.obtenerConfiteria(5);
            Assertions.assertNotNull(confiteria);
            System.out.println(confiteria.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteriaTest() throws Exception {

        try {
            Confiteria confiteriaGuardo = administradorServicio.obtenerConfiteria(5);
            System.out.println(confiteriaGuardo.getNombre());
            confiteriaGuardo.setNombre("super dog");
            Confiteria confiteriaActualizada= administradorServicio.actualizarConfiteria(confiteriaGuardo);
            Assertions.assertNotNull(confiteriaActualizada);
            System.out.println(confiteriaActualizada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteriaTest() throws Exception {

        try {
            administradorServicio.eliminarConfiteria(5);
            Confiteria confiteria= administradorServicio.obtenerConfiteria(5);
            Assertions.assertNull(confiteria);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteriaTest() {
        List<Confiteria> confiterias = administradorServicio.listarConfiteria();
        confiterias.forEach(System.out::println);
    }


    //------------------------------------------Cupones----------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCuponesTest(){
        try {
            Cupon cupon = new Cupon(0.15, LocalDateTime.now().toLocalDate(), "Registro", "bienvenido registro");
            Cupon cuponCreado= administradorServicio.crearCupones(cupon);
            Assertions.assertNotNull(cuponCreado);
            System.out.println(cuponCreado.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesTest() throws Exception {

        try {
            Cupon cupon = administradorServicio.obtenerCupones(5);
            Assertions.assertNotNull(cupon);
            System.out.println(cupon.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCuponesTest() throws Exception {

        try {
            Cupon cuponGuardado = administradorServicio.obtenerCupones(5);
            System.out.println(cuponGuardado.getCriterio());
            cuponGuardado.setCriterio("Navidad");
            Cupon cuponActualizado= administradorServicio.actualizarCupones(cuponGuardado);
            Assertions.assertNotNull(cuponActualizado);
            System.out.println(cuponActualizado.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuponesTest() throws Exception {
        try {
            administradorServicio.eliminarCupones(5);
            Cupon cupon= administradorServicio.obtenerCupones(5);
            Assertions.assertNull(cupon);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesTest() {
        List<Cupon> cupones = administradorServicio.listarCupones();
        cupones.forEach(System.out::println);
    }

}
