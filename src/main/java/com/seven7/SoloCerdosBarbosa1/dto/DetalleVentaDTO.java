package com.seven7.SoloCerdosBarbosa1.dto;


import lombok.*;


@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal;


}
