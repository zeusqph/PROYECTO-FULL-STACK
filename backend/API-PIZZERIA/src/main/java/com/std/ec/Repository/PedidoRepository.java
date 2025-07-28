package com.std.ec.Repository;
import com.std.ec.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {

}
