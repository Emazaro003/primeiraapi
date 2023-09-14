package org.elsr.service;

import java.util.List;
import java.util.Set;

import org.elsr.dto.PedidoDTO;
import org.elsr.entity.Pagamento;
import org.elsr.entity.Pedido;
import org.elsr.entity.Produto;
import org.elsr.repository.PedidoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoService {
    
    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ProdutoService produtoService;

    @Inject
    PagamentoService pagamentoService;

    public List<Pedido> findAllPedido(){
        return pedidoRepository.findAll().list();
    }

    public Pedido findPedido(Long id){
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido addPedido(PedidoDTO pedidoDTO) throws Exception{
        Pedido p = new Pedido();
        
        Produto prod = produtoService.findProduto(pedidoDTO.getProdutoId());
        Pagamento pag = pagamentoService.findPagamento(pedidoDTO.getPagamentoId());

        if (prod == null) {
            throw new Exception("Não foi possivel encotrar o produto");
        }else if (pag == null) {
            throw new Exception("Não foi possivel encotrar o pagamento");
        }

        p.setPagamento(pag);
        p.setProduto(prod);
        Set<String> labels = p.labelsPedido();
        p.setLabels(labels);

        pedidoRepository.persist(p);
        return p;
    }

    @Transactional
    public void editPedido(PedidoDTO pedidoDTO, Long id) throws Exception{
        Pedido p = pedidoRepository.findById(id);

        Produto prod = produtoService.findProduto(pedidoDTO.getProdutoId());
        Pagamento pag = pagamentoService.findPagamento(pedidoDTO.getPagamentoId());

        if (prod == null) {
            throw new Exception("Não foi possivel encotrar o produto");
        }else if (pag == null) {
            throw new Exception("Não foi possivel encotrar o pagamento");
        }

        p.setPagamento(pag);
        p.setProduto(prod);
        Set<String> labels = p.labelsPedido();
        p.setLabels(labels);

        pedidoRepository.persist(p);
    }

    @Transactional
    public boolean deletePedido(Long id){
        return pedidoRepository.deleteById(id);
    }
}
