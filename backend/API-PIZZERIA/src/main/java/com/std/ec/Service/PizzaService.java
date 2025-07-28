package com.std.ec.Service;

import com.std.ec.Entity.Pizza;
import com.std.ec.Repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository=pizzaRepository;
    }

    public Pizza crearPizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> obtenerTodasLasPizzas(){
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> obtenerPizzaPorId(Long id){
        return pizzaRepository.findById(id);
    }

    public Optional<Pizza> actualizaPizza(Long id,Pizza pizzaActualizada){
        return pizzaRepository.findById(id)
                .map(pizza -> {
                    pizza.setNombre(pizzaActualizada.getNombre());
                    pizza.setPrecio(pizzaActualizada.getPrecio());
                    pizza.setImagen(pizzaActualizada.getImagen());
                    pizza.setDescripcion(pizzaActualizada.getDescripcion());
                    pizza.setDisponible(pizzaActualizada.getDisponible());
                    pizza.setStock(pizzaActualizada.getStock());
                    return pizzaRepository.save(pizza);
                });
    }

    public Boolean eliminarPizza(Long id){
        if (pizzaRepository.existsById(id)){
            pizzaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
