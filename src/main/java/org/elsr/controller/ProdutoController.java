package org.elsr.controller;

import java.util.List;

import org.elsr.dto.ProdutoDTO;
import org.elsr.entity.Produto;
import org.elsr.service.ProdutoService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@Path("api/produto")
public class ProdutoController {
    
    @Inject
    ProdutoService produtoService;

    @GET
    public RestResponse<List<Produto>> getAllProduto(){
        return ResponseBuilder.ok(produtoService.findAllProduto()).build();
    }

    @GET
    @Path("/{id}")
    public RestResponse<Produto> getProduto(@PathParam("id") Long id){
        return ResponseBuilder.ok(produtoService.findProduto(id)).build();
    }

    @POST
    public RestResponse<Produto> postProduto(ProdutoDTO produtoDTO){
        if (produtoDTO == null) {
            ResponseBuilder.notFound().build();
        }
        return ResponseBuilder.create(Response.Status.CREATED, produtoService.addProduto(produtoDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Object> deleteProduto(@PathParam("id") Long id){
        if (produtoService.deleteProduto(id)){
            return ResponseBuilder.ok().build();
        }else{
            return ResponseBuilder.notFound().build();
        }
    }
}
