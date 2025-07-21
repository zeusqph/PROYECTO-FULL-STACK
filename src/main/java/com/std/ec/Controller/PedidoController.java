package com.std.ec.Controller;

import com.std.ec.DTO.PedidoDTO;
import com.std.ec.Entity.Pedido;
import com.std.ec.Service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ✅ Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    // ✅ Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // ✅ Obtener un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return (pedido != null) ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // ✅ Actualizar un pedido
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

    // ✅ Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        if (pedido != null) {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Obtener un pedido en formato DTO
    @GetMapping("/{id}/detalle")
    public ResponseEntity<PedidoDTO> obtenerPedidoDetalle(@PathVariable Long id) {
        Optional<PedidoDTO> pedidoDTO = pedidoService.obtenerPedidoDTO(id);
        return pedidoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Obtener todos los pedidos en formato DTO
    @GetMapping("/detalle")
    public ResponseEntity<List<PedidoDTO>> obtenerTodosPedidosDetalle() {
        List<PedidoDTO> pedidosDTO = pedidoService.obtenerTodosPedidosDTO();
        return ResponseEntity.ok(pedidosDTO);
    }
}

