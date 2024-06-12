package org.digitalthinking.resteasyjackson;


import org.digitalthinking.entites.Product;
import org.digitalthinking.repositories.ProductRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {
 @Inject
 ProductRepository repository;

    @GET
    public List<Product> list() {
        return repository.findAll();
    }

    @GET
    @Path("/{Id}")
    public Product getById(@PathParam("Id") Long Id) {
        return repository.findById(Id).get();
    }

    @POST
    public Response add(Product p) {
        repository.save(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@PathParam("Id") Long Id) {
         repository.delete( repository.findById(Id).get());
        return Response.ok().build();
    }
    @PUT
    public Response update(Product p) {
        Product product = repository.findById(p.getId()).get();
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        repository.save(product);
        return Response.ok().build();
    }


}
