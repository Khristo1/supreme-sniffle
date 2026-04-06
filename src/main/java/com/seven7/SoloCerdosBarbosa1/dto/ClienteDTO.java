package com.seven7.SoloCerdosBarbosa1.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String direccion;
}
