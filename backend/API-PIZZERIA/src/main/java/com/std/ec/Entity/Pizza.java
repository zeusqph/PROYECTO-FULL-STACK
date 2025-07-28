package com.std.ec.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Long id;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 255)
    private String imagen;

    @Column(length = 100)
    private String descripcion;

    @Column
    private Boolean disponible=true;

    @Column(nullable = false)
    private Integer stock;

    public Pizza () {}

    public Pizza(String nombre,Double precio,String imagen,String descripcion , Boolean disponible,Integer stock){
        this.nombre=nombre;
        this.precio=precio;
        this.imagen=imagen;
        this.descripcion=descripcion;
        this.disponible=disponible;
        this.stock=stock;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Boolean getDisponible() {
        return disponible;
    }
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
