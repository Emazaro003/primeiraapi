package org.elsr.controller;

import java.util.List;

import org.elsr.dto.ProdutoDTO;
import org.elsr.entity.Produto;
import org.elsr.service.ProdutoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


@Path("api/produto")
public class ProdutoController {
    
    @Inject
    ProdutoService produtoService;

    @GET
    public List<Produto> getAllProduto(){
        return produtoService.findAllProduto();
    }

    @GET
    @Path("/{id}")
    public Produto getProduto(@PathParam("id") Long id){
        return produtoService.findProduto(id);
    }

    @POST
    public void postProduto(ProdutoDTO produtoDTO){
        // if (produtoDTO == null) {
        //     Break
        // }
        produtoService.addProduto(produtoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteProduto(@PathParam("id") Long id){
        produtoService.deleteProduto(id);
    }
}
