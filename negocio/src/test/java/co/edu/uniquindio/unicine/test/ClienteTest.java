package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrar(){
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", "3117556502", "url");
        Cliente clienteGuardado = clienteRepo.save(cliente);
        Assertions.assertEquals("Juan", clienteGuardado.getNombre());
    }

    @Test
    public void eliminar(){
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", "3117556502", "url");
        Cliente clienteGuardado = clienteRepo.save(cliente);
        clienteRepo.delete(clienteGuardado);

        Optional<Cliente> clienteBuscado = clienteRepo.findById(1004);

        Assertions.assertNull(clienteBuscado.orElse(null));
    }

    @Test
    public void actualizar(){
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", "3117556502", "url");
        Cliente clienteGuardado = clienteRepo.save(cliente);

        clienteGuardado.setNombre("Jose");

        Cliente clienteNuevo = clienteRepo.save(clienteGuardado);

        Assertions.assertEquals("Jose", clienteNuevo.getNombre());
    }

    @Test
    public void obtener(){
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", "3117556502", "url");
        clienteRepo.save(cliente);

        Optional<Cliente> clienteBuscado = clienteRepo.findById(1004);
        System.out.println(clienteBuscado.orElse(null));
    }

    @Test
    public void listar(){
        Cliente cliente = new Cliente(1004,"Juan", "perdomo@gmail.com", "2208", "3117556502", "url");
        clienteRepo.save(cliente);

        Cliente cliente1 = new Cliente(1006,"Jose", "perdomov.j07@gmail.com", "2208", "3117556502", "url");
        clienteRepo.save(cliente1);

        List<Cliente> listaClientes = clienteRepo.findAll();

        System.out.println(listaClientes);
    }
}
