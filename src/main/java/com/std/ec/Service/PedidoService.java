package com.std.ec.Service;

import com.std.ec.Entity.Pedido;
import com.std.ec.Repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;


public class PedidoService {

    public final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository=pedidoRepository;
    }

    public List<Pedido> obtenerPedido(){
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id).orElse(null);
    }

    public void eliminarPedido(Long id){
        pedidoRepository.deleteById(id);
    }
}
