package com.seven7.SoloCerdosBarbosa1.controller;

import com.seven7.SoloCerdosBarbosa1.dto.VentaDTO;
import com.seven7.SoloCerdosBarbosa1.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas(){
        List<VentaDTO> ventas = ventaService.traerVentas();
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO dto){
        VentaDTO created = ventaService.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Long id, @RequestBody VentaDTO dto){
        VentaDTO updated = ventaService.actualizarVenta(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}