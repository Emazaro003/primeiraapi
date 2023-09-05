package org.elsr.service;

import java.util.List;

import org.elsr.dto.ProdutoDTO;
import org.elsr.entity.Produto;
import org.elsr.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    public List<Produto> findAllProduto(){
        return produtoRepository.findAll().list();
    }

    public Produto findProduto(Long id){
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto addProduto(ProdutoDTO produtoDTO){
        Produto p = new Produto();
        p.setNome(produtoDTO.getNome());
        p.setCategoria(produtoDTO.getCategoria());
        p.setPreco(produtoDTO.getPreco());

        produtoRepository.persist(p);
        return p;
    }

    @Transactional
    public void editProduto(ProdutoDTO produtoDTO, Long id){
        Produto p = produtoRepository.findById(id);

        p.setNome(produtoDTO.getNome());
        p.setCategoria(produtoDTO.getCategoria());
        p.setPreco(produtoDTO.getPreco());

        produtoRepository.persist(p);
    }

    @Transactional
    public boolean deleteProduto(Long id){
        return produtoRepository.deleteById(id);
    }
}
