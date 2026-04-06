package com.seven7.SoloCerdosBarbosa1.mapper;

import com.seven7.SoloCerdosBarbosa1.dto.ClienteDTO;
import com.seven7.SoloCerdosBarbosa1.dto.DetalleVentaDTO;
import com.seven7.SoloCerdosBarbosa1.dto.ProductoDTO;
import com.seven7.SoloCerdosBarbosa1.dto.VentaDTO;
import com.seven7.SoloCerdosBarbosa1.model.Cliente;
import com.seven7.SoloCerdosBarbosa1.model.Producto;
import com.seven7.SoloCerdosBarbosa1.model.Venta;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    //mapeo de producto a ProductoDTO

    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

   // Mapeo de venta a VentaDTO
   public static VentaDTO toDTO(Venta venta) {
       if (venta == null) return null;

       List<DetalleVentaDTO> detalle = venta.getDetalle().stream().map(det ->
               DetalleVentaDTO.builder()
                       .id(det.getId())
                       .nombreProd(det.getProd() != null ? det.getProd().getNombre() : "Producto no disponible")
                       .cantProd(det.getCantProd())
                       .precio(det.getPrecio())
                       .subtotal(det.getPrecio() * det.getCantProd())
                       .build()
       ).collect(Collectors.toList());

       Double total = detalle.stream()
               .map(DetalleVentaDTO::getSubtotal)
               .reduce(0.0, Double::sum);

       return VentaDTO.builder()
               .id(venta.getId())
               .fecha(venta.getFecha())
               .idCliente(venta.getCliente() != null ? venta.getCliente().getId() : null)
               .estado(venta.getEstado())
               .detalle(detalle)
               .total(total)
               .build();
   }

    // Mapeo de cliente a ClienteDTO
    public static ClienteDTO toDTO(Cliente c) {
        if (c == null) return null;
        return ClienteDTO.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .direccion(c.getDireccion())
                .build();
    }
}
