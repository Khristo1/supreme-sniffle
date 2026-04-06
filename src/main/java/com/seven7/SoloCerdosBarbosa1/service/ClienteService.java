package com.seven7.SoloCerdosBarbosa1.service;

import com.seven7.SoloCerdosBarbosa1.dto.ClienteDTO;
import com.seven7.SoloCerdosBarbosa1.exception.NotFoundException;
import com.seven7.SoloCerdosBarbosa1.mapper.Mapper;
import com.seven7.SoloCerdosBarbosa1.model.Cliente;
import com.seven7.SoloCerdosBarbosa1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private ClienteRepository repo;


    @Override
    public List<ClienteDTO> traerClientes() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDto) {
        Cliente cli= Cliente.builder()
                .nombre(clienteDto.getNombre())
                .direccion(clienteDto.getDireccion())
                .build();

        return Mapper.toDTO(repo.save(cli));
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDto) {
        Cliente cli= repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        cli.setNombre(clienteDto.getNombre());
        cli.setDireccion(clienteDto.getDireccion());

        return Mapper.toDTO(repo.save(cli));
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!repo.existsById(id))
            throw new NotFoundException("cliente no encontrado");

        repo.deleteById(id);

    }
}
