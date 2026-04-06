package com.seven7.SoloCerdosBarbosa1.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {

    //datos de la venta

    private Long id;
    private LocalDate fecha;
    private String estado;

    //datos del cliente
    private Long idCliente;

    //lista de detalles
    private List<DetalleVentaDTO> detalle;

    //total de la venta
    private Double total;

}
