package org.elsr.service;

import java.util.List;

import org.elsr.dto.PagamentoDTO;
import org.elsr.entity.Pagamento;
import org.elsr.repository.PagamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAllPagamento(){
        return pagamentoRepository.findAll().list();
    }

    public Pagamento findPagamento(Long id){
        return pagamentoRepository.findById(id);
    }

    @Transactional
    public Pagamento addPagamento(PagamentoDTO pagamentoDTO){
        Pagamento p = new Pagamento();
        p.setFormaDePagamento(pagamentoDTO.getFormaDePagamento());
        p.setValor(pagamentoDTO.getValor());

        pagamentoRepository.persist(p);
        return p;
    }

    @Transactional
    public void editPagamento(PagamentoDTO pagamentoDTO, Long id){
        Pagamento p = pagamentoRepository.findById(id);

        p.setFormaDePagamento(pagamentoDTO.getFormaDePagamento());
        p.setValor(pagamentoDTO.getValor());

        pagamentoRepository.persist(p);
    }

    @Transactional
    public boolean deletePagamento(Long id){
        return pagamentoRepository.deleteById(id);
    }
}
