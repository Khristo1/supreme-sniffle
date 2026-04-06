package com.seven7.SoloCerdosBarbosa1.dto;


import lombok.*;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;
}
