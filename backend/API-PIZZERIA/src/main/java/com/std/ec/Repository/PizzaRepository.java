package com.std.ec.Repository;

import com.std.ec.Entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository <Pizza, Long>{
}
