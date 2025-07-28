package com.std.ec.Controller;

import com.std.ec.DTO.PedidoDTO;
import com.std.ec.Entity.Pedido;
import com.std.ec.Service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }


    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        return ResponseEntity.ok(pedidos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return (pedido != null) ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Pedido pedidoExistente = pedidoService.obtenerPedidoPorId(id);
        if (pedidoExistente != null) {
            pedidoExistente.setDireccion(pedidoActualizado.getDireccion());
            pedidoExistente.setEstado(pedidoActualizado.getEstado());
            Pedido actualizado = pedidoService.guardarPedido(pedidoExistente);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        if (pedido != null) {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/detalle")
    public ResponseEntity<PedidoDTO> obtenerPedidoDetalle(@PathVariable Long id) {
        Optional<PedidoDTO> pedidoDTO = pedidoService.obtenerPedidoDTO(id);
        return pedidoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/detalle")
    public ResponseEntity<List<PedidoDTO>> obtenerTodosPedidosDetalle() {
        List<PedidoDTO> pedidosDTO = pedidoService.obtenerTodosPedidosDTO();
        return ResponseEntity.ok(pedidosDTO);
    }
}

