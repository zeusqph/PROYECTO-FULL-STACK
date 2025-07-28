package com.std.ec.Controller;


import com.std.ec.DTO.DetallePedidoDTO;
import com.std.ec.DTO.DetallePedidoRequest;
import com.std.ec.Service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService){
        this.detallePedidoService=detallePedidoService;
    }

    @PostMapping("/{pedidoId}/detalles")
    public ResponseEntity<DetallePedidoDTO> agregarDetalle(
            @PathVariable Long pedidoId,
            @RequestBody DetallePedidoRequest request){

        DetallePedidoDTO nuevoDetalle = detallePedidoService.agregarDetalle(
                pedidoId,
                request.getPizzaId(),
                request.getCantidad()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalle);
    }

    @GetMapping("/{pedidoId}/detalles")
    public ResponseEntity<List<DetallePedidoDTO>>obtenerDetallesPorPedido(@PathVariable Long pedidoId){
        List<DetallePedidoDTO> detalles = detallePedidoService.obtenerDetallesPorPedido(pedidoId);
        return ResponseEntity.ok(detalles);
    }

    @PutMapping("/detalles/{detalleId}")
    public ResponseEntity<DetallePedidoDTO>actualizarDetalle(
        @PathVariable Long detalleId,
        @RequestBody DetallePedidoRequest request){

        DetallePedidoDTO actualizado=detallePedidoService.actualizarDetalle(
                detalleId,
                request.getPizzaId(),
                request.getCantidad()
        );
        return  ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/detalles/{detalleId}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long detalleId) {
        detallePedidoService.eliminarDetalle(detalleId);
        return ResponseEntity.noContent().build();
    }

}
