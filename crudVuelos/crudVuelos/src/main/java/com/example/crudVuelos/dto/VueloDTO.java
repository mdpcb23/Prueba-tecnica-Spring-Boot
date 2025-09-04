package com.example.crudVuelos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VueloDTO {
    private Integer idV;
    private String nombreV;
    private String aereolinea;
    private String ciudadSalida;
    private String ciudadLlegada;
    private LocalDate diaInicio;
    private LocalDate diaFinal;
    private int duracionEnDias;
}
