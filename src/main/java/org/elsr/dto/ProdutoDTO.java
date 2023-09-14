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

    public boolean produtoValidate(){
        if (this.Nome.equals("")){
            return false;
        }
        if (this.Categoria.equals("")){
            return false;
        }
        if (this.Preco <= 1){
            return false;
        }
        return true;
    }

}
