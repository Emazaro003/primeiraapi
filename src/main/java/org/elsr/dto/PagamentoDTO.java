package org.elsr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PagamentoDTO {
    
    private String formaDePagamento;

    private double valor;
}
