package org.elsr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProdutoDTO {
    
    private String Nome;

    private String Categoria;

    private double Preco;

}
