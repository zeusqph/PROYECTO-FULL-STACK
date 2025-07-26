package com.std.ec.DTO;

public class DetallePedidoRequest {

    private Long pizzaId;
    private Integer cantidad;

    public  DetallePedidoRequest(){}

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
