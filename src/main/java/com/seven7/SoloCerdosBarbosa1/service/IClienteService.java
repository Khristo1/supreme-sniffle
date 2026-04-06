package com.seven7.SoloCerdosBarbosa1.service;

import com.seven7.SoloCerdosBarbosa1.dto.ClienteDTO;


import java.util.List;

public interface IClienteService {

    List<ClienteDTO> traerClientes();
    ClienteDTO crearCliente(ClienteDTO clienteDto);
    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDto);
    void eliminarCliente(Long id);

}
