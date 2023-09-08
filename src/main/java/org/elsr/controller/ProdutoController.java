package org.elsr.controller;

import java.net.URI;

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
    public RestResponse<Object> getAllProduto(){
        return ResponseBuilder.ok().entity(produtoService.findAllProduto()).build();
    }

    @GET
    @Path("/{id}")
    public RestResponse<Object> getProduto(@PathParam("id") Long id){
        Produto p = produtoService.findProduto(id);
        if (p == null){
            return ResponseBuilder.notFound().build();
        }
        return ResponseBuilder.ok().entity(p).build();
    }

    @POST
    public Response postProduto(ProdutoDTO produtoDTO){
        if (produtoDTO.getNome() == "" || produtoDTO.getCategoria() == "" || produtoDTO.getPreco() <= 1) {
            return Response.status(400).entity("Dados invalidos").build();
        }
        try {
            Produto p = produtoService.addProduto(produtoDTO);
            URI uri = new URI("api/produto/"+ p.getId());
            return Response.created(uri).entity(p).build();
        } catch (Exception e) {
            return Response.status(500).entity("Nao foi possivel encontrar o id").build();
        }
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
