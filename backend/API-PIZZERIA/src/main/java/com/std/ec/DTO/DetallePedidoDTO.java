package com.std.ec.DTO;

public class DetallePedidoDTO {
    private Long id ;
    private String nombrePizza;
    private Integer cantidad ;
    private Double subtotatl;

    public DetallePedidoDTO(Long id,String nombrePizza,Integer cantidad,Double subtotatl){
        this.id=id;
        this.nombrePizza=nombrePizza;
        this.cantidad=cantidad;
        this.subtotatl=subtotatl;
    }

    public Long getId() {
        return id;
    }

    public String getNombrePizza() {
        return nombrePizza;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getSubtotatl() {
        return subtotatl;
    }
}
