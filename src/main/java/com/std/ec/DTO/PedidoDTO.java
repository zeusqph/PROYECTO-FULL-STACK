package com.std.ec.DTO;

import java.time.LocalDateTime;
public class PedidoDTO {

    private Long id;
    private String direccion;
    private Boolean estado;
    private LocalDateTime fecha;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private String distritoCliente;


    public PedidoDTO(Long id,String direccion,Boolean estado ,LocalDateTime fecha, String nombreCliente,
                     String correoCliente, String telefonoCliente, String distritoCliente){
        this.id=id;
        this.direccion=direccion;
        this.estado=estado;
        this.fecha=fecha;
        this.nombreCliente=nombreCliente;
        this.correoCliente=correoCliente;
        this.telefonoCliente=telefonoCliente;
        this.distritoCliente=distritoCliente;

    }

    public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public String getDistritoCliente() {
        return distritoCliente;
    }
}
