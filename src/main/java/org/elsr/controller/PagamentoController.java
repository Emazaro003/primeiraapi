package org.elsr.controller;

import java.net.URI;

import org.elsr.dto.PagamentoDTO;
import org.elsr.entity.Pagamento;
import org.elsr.service.PagamentoService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("api/pagamento")
public class PagamentoController {
    
    @Inject
    PagamentoService pagamentoService;

    @GET
    public RestResponse<Object> getAllPagamento(){
        return ResponseBuilder.ok().entity(pagamentoService.findAllPagamento()).build();
    }

    @GET
    @Path("/{id}")
    public RestResponse<Object> getPagamento(@PathParam("id") Long id){

        Pagamento p = pagamentoService.findPagamento(id);

        if (p == null) {
            return ResponseBuilder.notFound().build();
        }
        
        return ResponseBuilder.ok().entity(p).build();

    }

    @POST
    public Response postPagamento(PagamentoDTO pagamentoDTO){

        if (pagamentoDTO.getFormaDePagamento().equals("") || pagamentoDTO.getValor() <= 1) {
            return Response.status(400).entity("Dados invalidos").build();
        }

        try {
            Pagamento p = pagamentoService.addPagamento(pagamentoDTO);
            URI uri = new URI("api/produto/"+ p.getId());
            return Response.created(uri).entity(p).build();
        } catch (Exception e) {
            return Response.status(500).entity("Nao foi possivel encontrar o id").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Object> deletePagamento(@PathParam("id") Long id){
        if (pagamentoService.deletePagamento(id)) {
            return ResponseBuilder.ok().build();
        } else {
            return ResponseBuilder.notFound().build();
        }
    }

}
