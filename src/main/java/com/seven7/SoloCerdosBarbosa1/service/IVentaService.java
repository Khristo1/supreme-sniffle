package com.seven7.SoloCerdosBarbosa1.service;

import com.seven7.SoloCerdosBarbosa1.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> traerVentas();
    VentaDTO crearVenta(VentaDTO ventaDto);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);
    void eliminarVenta(Long id);
}
