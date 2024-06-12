package org.digitalthinking.resteasyjackson;



import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.digitalthinking.entites.Customer;

import org.digitalthinking.entites.Product;
import org.digitalthinking.repositories.CustomerRepository;
import org.jboss.resteasy.reactive.RestPath;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jetbrains.annotations.Blocking;

import java.util.List;



@Slf4j
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CustomerApi {
 @Inject
 CustomerRepository repository;


    @GET
    public Uni<List<PanacheEntityBase>> list() {
        return Customer.listAll(Sort.by("names"));
    }


    @GET
    @Path("using-repository")
    public Uni<List<Customer>> listUsingRepository() {
        return repository.findAll().list();
    }

    @GET
    @Path("/{Id}")
    public Uni<PanacheEntityBase> getById(@PathParam("Id") Long Id) {
        return Customer.findById(Id);
    }

    @GET
    @Path("/{Id}/product")
    public Uni<Customer> getByIdProduct(@PathParam("Id") Long Id) {
     return repository.getByIdProduct(Id);
    }

    @POST
    public Uni<Response> add(Customer c) {
      return repository.add(c);
    }

    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) {
        return repository.delete(Id);
    }
    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Long id, Customer c) {
       return repository.update(id,c);
    }


    @GET
    @Path("/products-grapql")
    @Blocking
    public List<Product> getProductsGrapQl() throws Exception{
        return repository.getProductsGrapQl();
    }

    @GET
    @Path("/{Id}/product-grapql")
    @Blocking
    public Product getByIdProductGrapQl(@PathParam("Id") Long Id) throws Exception{
        return repository.getByIdProductGrapQl(Id);
    }



}
