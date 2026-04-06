package com.seven7.SoloCerdosBarbosa1.controller;

import com.seven7.SoloCerdosBarbosa1.dto.ClienteDTO;
import com.seven7.SoloCerdosBarbosa1.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> traerClientes(){
        return ResponseEntity.ok().body(clienteService.traerClientes());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto){
        ClienteDTO created = clienteService.crearCliente(dto);
        return ResponseEntity.created(URI.create("/api/clientes" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto){
        return ResponseEntity.ok(clienteService.actualizarCliente(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
