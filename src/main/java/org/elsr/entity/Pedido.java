package org.elsr.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name = "produtoid")
    private Produto Produto;

    @ManyToOne
    @JoinColumn(name = "pagamentoid")
    private Pagamento Pagamento;

    @ElementCollection
    @CollectionTable(
        name = "pedido_labels",
        joinColumns = @JoinColumn(name = "pedido_id")
    )
    @Column(name = "label")
    private Set<String> labels;

    private double total;


    public Set<String> labelsPedido() {

        Set<String> labels = new HashSet<String>();
        this.total = this.getPagamento().getValor();

        if (this.Produto.getPreco() > 1000) {
            labels.add("frete-gratis");
        }if (this.Produto.getCategoria().equals("eletrodomestico")) {
            labels.add("fragil");
        }if (this.Produto.getCategoria().equals("infantil")) {
            labels.add("presente");
        }if (this.Pagamento.getFormaDePagamento().equals("boleto")) {
            this.total = this.getPagamento().getValor() * 0.9;
        }

        return labels;
    }
}
