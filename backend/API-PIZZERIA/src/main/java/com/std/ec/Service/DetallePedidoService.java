package com.std.ec.Service;

import com.std.ec.Entity.DetallePedido;
import com.std.ec.DTO.DetallePedidoDTO;
import com.std.ec.Entity.Pedido;
import com.std.ec.Entity.Pizza;
import com.std.ec.Repository.DetallePedidoRepository;
import com.std.ec.Repository.PedidoRepository;
import com.std.ec.Repository.PizzaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final PizzaRepository pizzaRepository;


    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository,
                                PedidoRepository pedidoRepository,
                                PizzaRepository pizzaRepository){
        this.detallePedidoRepository=detallePedidoRepository;
        this.pedidoRepository=pedidoRepository;
        this.pizzaRepository=pizzaRepository;

    }

    public DetallePedidoDTO agregarDetalle(Long pedidoId, Long pizzaId, Integer cantidad){

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido Encontrado"));

        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new RuntimeException("Pizza no Encontrada"));

        // calcular el precio total
        double subtotal = pizza.getPrecio() * cantidad;
        // guardar todos los detalles del pedido
        DetallePedido detalle = new DetallePedido(pedido,pizza,cantidad,subtotal);

        DetallePedido guardado = detallePedidoRepository.save(detalle);

        if (pizza.getStock()<  cantidad ){
            throw new RuntimeException("Stock insuficiente para la pizza " +pizza.getNombre());
        }
        pizza.setStock(pizza.getStock() - cantidad);
        pizzaRepository.save(pizza);

        return convertirADTO(guardado);
    }



    public List<DetallePedidoDTO> obtenerDetallesPorPedido( Long pedidoId) {
        List<DetallePedido> detalles =  detallePedidoRepository.findByPedidoId(pedidoId);
        return detalles.stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DetallePedidoDTO actualizarDetalle(Long detalleId,Long nuevaPizzaId,Integer nuevaCantidad){
        DetallePedido detalle=detallePedidoRepository.findById(detalleId)
                .orElseThrow(()->new RuntimeException("Detalle no encontrado"));

        Pizza pizzaAnterior = detalle.getPizza();
        int cantidadAnterior = detalle.getCantidad();

        if(!pizzaAnterior.getId().equals(nuevaPizzaId)){
            Pizza nuevaPizza=pizzaRepository.findById(nuevaPizzaId)
                    .orElseThrow(() ->new RuntimeException("Pizza no encontrada"));

            pizzaAnterior.setStock(pizzaAnterior.getStock() + cantidadAnterior);
            pizzaRepository.save(pizzaAnterior);

            if (nuevaPizza.getStock() < nuevaCantidad){
                throw new RuntimeException("Stock insuficiente para la nueva pizza");
            }

            detalle.setPizza(nuevaPizza);
            detalle.setCantidad(nuevaCantidad);
            detalle.setSubtotal(nuevaPizza.getPrecio() * nuevaCantidad);

            nuevaPizza.setStock(nuevaPizza.getStock() - nuevaCantidad);
            pizzaRepository.save(nuevaPizza);


        }else{

            int diferencia = nuevaCantidad - cantidadAnterior;
            if(diferencia > 0 && pizzaAnterior.getStock() < diferencia){
                throw new RuntimeException("Stock insuficiente para actualizar cantidad");

            }
            detalle.setCantidad(nuevaCantidad);
            detalle.setSubtotal(pizzaAnterior.getPrecio() * nuevaCantidad);

            pizzaAnterior.setStock(pizzaAnterior.getStock() - diferencia);
            pizzaRepository.save(pizzaAnterior);
        }
        DetallePedido actualizado= detallePedidoRepository.save(detalle);
        return  convertirADTO(actualizado);
    }

    public void eliminarDetalle(Long detalleId){
        DetallePedido detalle =detallePedidoRepository.findById(detalleId)
                .orElseThrow(()-> new RuntimeException("Detalle no encontrado"));

        Pizza pizza =detalle.getPizza();
        pizza.setStock(pizza.getStock() + detalle.getCantidad());
        pizzaRepository.save(pizza);

        detallePedidoRepository.delete(detalle);
    }

    private DetallePedidoDTO convertirADTO(DetallePedido detalle){
        return new DetallePedidoDTO(
                detalle.getId(),
                detalle.getPizza().getNombre(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }
}
