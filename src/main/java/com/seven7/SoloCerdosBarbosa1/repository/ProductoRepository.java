package com.seven7.SoloCerdosBarbosa1.repository;

import com.seven7.SoloCerdosBarbosa1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    //buscar producto por nombre

    Optional<Producto> findByNombre(String nombre);
}
