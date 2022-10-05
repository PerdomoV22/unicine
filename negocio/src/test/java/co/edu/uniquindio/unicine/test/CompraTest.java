package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.dto.InformacionCompraDTO;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradaCompras() {
        List<Entrada> listaEntradas = compraRepo.obtenerEntradas(1);
        Assertions.assertEquals(2, listaEntradas.size());
        listaEntradas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void contarCuponesRedimidos() {
        List<Object[]> listaCupones = compraRepo.contarCuponesRedimidos();
        listaCupones.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void calcularValorTotalGastados() {
        Double valorTotal = compraRepo.calcularValorTotalGastado(1004);
        //Assertions.assertEquals();
        System.out.println(valorTotal);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraMaxCostosa() {
        List<Object[]> comprasCostosas = compraRepo.obtenerCompraMaxCostasa();
        comprasCostosas.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerInformacionUsuario(){
        List<InformacionCompraDTO> listaInformacion = compraRepo.obtenerInformacionUsuario(1004);
        listaInformacion.forEach(System.out::println);
    }*/

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaMasVista() {
        List<Object[]> peliculas = compraRepo.obtenerPeliculaMasVista(1);
        peliculas.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }
}
