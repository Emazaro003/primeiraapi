package org.elsr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PedidoDTO {

    private Long ProdutoId;

    private Long PagamentoId;

    public boolean pedidoValidate(){
        if (this.ProdutoId < 0) {
            return false;
        }if (this.ProdutoId < 0) {
            return false;
        }

        return true;
    }
}
