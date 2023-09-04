package org.elsr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue
    private Long Id;

    private String Nome;

    private String Categoria;

    private double Preco;

}
