package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.InformacionCompraDTO;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select e from Compra compra join compra.entradas e where compra.codigo = :codigo")
    List<Entrada> obtenerEntradas(Integer codigo);

    @Query("select compra.cliente.cedula, count(compra) from Compra compra where compra.cupon is not null group by compra.cliente")
    List<Object[]> contarCuponesRedimidos();

    @Query("select sum(c.valorTotal) from Compra c where c.cliente.cedula = :codigoCliente")
    Double calcularValorTotalGastado(Integer codigoCliente);

    @Query("select c1.cliente, c1 from Compra c1 where c1.valorTotal = (select max(c.valorTotal) from Compra c)")
    List<Object[]> obtenerCompraMaxCostasa();

    @Query("select new co.edu.uniquindio.unicine.dto.InformacionCompraDTO( c.valorTotal, c.fechaCompra, c.funcion, (select sum(e.precio) from Entrada e where e.compra.codigo = c.codigo), (select sum(cc.precio*cc.unidades) from CompraConfiteria cc where cc.compra.codigo = c.codigo)) from Compra c where c.cliente.cedula = :codigoCliente")
    List<InformacionCompraDTO> obtenerInformacionUsuario(Integer codigoCliente);

    @Query("select c.funcion.pelicula, count(c) from Compra c where c.funcion.sala.teatro.ciudad.codigoPostal = :codigoCiudad group by c.funcion.pelicula")
    List<Object[]> obtenerPeliculaMasVista(Integer codigoCiudad);


}
