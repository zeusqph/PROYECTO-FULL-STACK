package com.std.ec.Controller;

import com.std.ec.Entity.Pizza;
import com.std.ec.Service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {


    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService ;
    }

    // Metodo POST - CREAR
    @PostMapping
    public ResponseEntity<Pizza>crearPizza(@RequestBody Pizza pizza){
        Pizza nuevaPizza = pizzaService.crearPizza(pizza);
        return  ResponseEntity.status(HttpStatus.CREATED).body(nuevaPizza);
    }


    // Metodo GET - listar odas las pizzas
    @GetMapping
    public List<Pizza> obtenerTodasLasPizzas(){
        return pizzaService.obtenerTodasLasPizzas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> obtenerPizzaPorId(@PathVariable Long id){
        return pizzaService.obtenerPizzaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> actualizaPizza(@PathVariable Long id, @RequestBody Pizza pizzaActualizada){
        return pizzaService.actualizaPizza(id, pizzaActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPizza(@PathVariable Long id){
        if (pizzaService.eliminarPizza(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
