package com.std.ec.Service;

import com.std.ec.DTO.PedidoDTO;
import com.std.ec.Entity.Cliente;
import com.std.ec.Entity.Pedido;
import com.std.ec.Repository.ClienteRepository;
import com.std.ec.Repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    // ✅ Crear o guardar pedido (asociando el cliente real)
    public Pedido guardarPedido(Pedido pedido) {
        Long clienteId = pedido.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        pedido.setCliente(cliente);
        return pedidoRepository.save(pedido);
    }

    // ✅ Obtener todos los pedidos (entidad)
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    // ✅ Obtener pedido por ID (entidad)
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    // ✅ Eliminar pedido
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    // ✅ Obtener pedido por ID en formato DTO
    public Optional<PedidoDTO> obtenerPedidoDTO(Long id) {
        return pedidoRepository.findById(id)
                .map(this::convertirAPedidoDTO);
    }

    // ✅ Obtener todos los pedidos en formato DTO
    public List<PedidoDTO> obtenerTodosPedidosDTO() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::convertirAPedidoDTO)
                .toList();
    }

    // ✅ Convertir Pedido a PedidoDTO
    public PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getDireccion(),
                pedido.getEstado(),
                pedido.getFecha(),
                pedido.getCliente().getNombre(),
                pedido.getCliente().getCorreo(),
                pedido.getCliente().getTelefono(),
                pedido.getCliente().getDistrito()
        );
    }
}

