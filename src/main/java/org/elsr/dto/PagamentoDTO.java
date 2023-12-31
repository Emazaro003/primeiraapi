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

    public boolean pagamentoValidate(){
        if (this.formaDePagamento.equals("")){
            return false;
        }
        if (this.valor <= 1){
            return false;
        }
        return true;
    }
}
