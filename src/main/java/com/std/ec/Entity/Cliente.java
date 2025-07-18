package com.std.ec.Entity;
import jakarta.persistence.*;
@Entity

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id ;

    @Column(nullable = false,length =40)
    private String nombre;

    @Column (length = 15)
    private String telefono;

    @Column (length = 50)
    private String correo ;

    @Column (length = 50)
    private String ubicacion;

    public Cliente(){}

    public Cliente (String nombre , String telefono , String correo , String ubicacion){
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo ;
        this.ubicacion = ubicacion ;

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


    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
