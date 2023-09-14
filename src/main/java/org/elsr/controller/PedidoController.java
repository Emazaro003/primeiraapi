package org.elsr.controller;

import java.net.URI;

import org.elsr.dto.PedidoDTO;
import org.elsr.entity.Pedido;
import org.elsr.service.PedidoService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("api/pedido")
public class PedidoController {
    @Inject
    PedidoService pedidoService;

    @GET
    public RestResponse<Object> getAllPedido(){
        return ResponseBuilder.ok().entity(pedidoService.findAllPedido()).build();
    }

    @GET
    @Path("/{id}")
    public RestResponse<Object> getPagamento(@PathParam("id") Long id){

        Pedido p = pedidoService.findPedido(id);

        if (p == null) {
            return ResponseBuilder.notFound().build();
        }
        
        return ResponseBuilder.ok().entity(p).build();

    }

    @POST
    public Response postPagamento(PedidoDTO pedidoDTO){

        if (!pedidoDTO.pedidoValidate()) {
            return Response.status(400).entity("Dados invalidos").build();
        }

        try {
            Pedido p = pedidoService.addPedido(pedidoDTO);
            URI uri = new URI("api/pedido/"+ p.getId());
            return Response.created(uri).entity(p).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Object> deletePagamento(@PathParam("id") Long id){
        if (pedidoService.deletePedido(id)) {
            return ResponseBuilder.ok().build();
        } else {
            return ResponseBuilder.notFound().build();
        }
    }
}
