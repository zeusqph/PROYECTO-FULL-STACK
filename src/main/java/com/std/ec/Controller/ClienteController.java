package com.std.ec.Controller;

import com.std.ec.Entity.Cliente;
import com.std.ec.Repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository  clienteRepository;

    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }


    @PostMapping
    public ResponseEntity <Cliente> crearCliente(@RequestBody Cliente cliente){
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente>getCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente>actualizarClientes(@PathVariable Long id,@RequestBody Cliente clienteActualizado){
        return  clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setCorreo(clienteActualizado.getCorreo());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setUbicacion(clienteActualizado.getUbicacion());
                    Cliente actualizado = clienteRepository.save(cliente);
                    return ResponseEntity.ok(actualizado); // El estado sera de 200 si se actualizo el cliente
                })
                .orElse(ResponseEntity.notFound().build()); // El estado sera de 404 si no Existe
    }

    @DeleteMapping("/{id}")
    private ResponseEntity <Void> eliminarCliente (@PathVariable Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Si se elimino el usuario , regresara en estado 204 noContent
        }else {
            return ResponseEntity.notFound().build(); // Si no se elimino el usuario , regresara el estado 404 notFound
        }
    }


}
