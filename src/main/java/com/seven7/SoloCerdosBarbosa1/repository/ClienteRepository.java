package com.seven7.SoloCerdosBarbosa1.repository;

import com.seven7.SoloCerdosBarbosa1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
