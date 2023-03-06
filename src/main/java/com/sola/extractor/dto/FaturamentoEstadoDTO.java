package com.sola.extractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaturamentoEstadoDTO {
    private String estado;
    private Double valor;
}

