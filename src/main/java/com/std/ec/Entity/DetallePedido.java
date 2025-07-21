package com.std.ec.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_pedido",nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "pizza_id",nullable = false)
    private Pizza pizza;


    @Column(nullable = false)
    private Integer cantidad;

    @Column
    private Double subtotal;

    public DetallePedido() {}

    public DetallePedido(Pedido pedido , Pizza pizza , Integer cantidad , Double subtotal){
        this.pedido=pedido;
        this.pizza=pizza;
        this.cantidad=cantidad;
        this.subtotal=subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
