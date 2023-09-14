package org.elsr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
public class Pagamento {
    
    @Id
    @GeneratedValue
    private Long Id;

    private String FormaDePagamento;

    private double Valor;
}
