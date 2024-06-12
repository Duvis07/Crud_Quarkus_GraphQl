package org.digitalthinking.graphqlapi;

import io.smallrye.mutiny.Uni;
import org.digitalthinking.entites.Product;
import org.digitalthinking.repositories.ProductRepository;
import org.eclipse.microprofile.graphql.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@GraphQLApi
public class ProdutResource {

    @Inject
    ProductRepository repository;

    @Query("allProducts")
    @Description("Get all products from a database")
    public List<Product> getAllProducts(){
        return  repository.findAll();
    }

    @Query("product")
    @Description("Get a product from database")
    public Optional<Product> getProduct(@Name("productId") Long id) {
        return repository.findById(id);
    }
    

  //Optional methods that not was requiered on Chanlenge
    @Mutation
    @Description("Add a new product to database")
    public Product addProduct(@Name("product") Product product) {
        return repository.save(product);
    }

    @Mutation
    @Description("Update a product in database")
    public Product updateProduct(@Name("product") Product product) {
        return repository.save(product);
    }

    @Mutation
    @Description("Delete a product by ID")
    public boolean deleteProduct(@Name("productId") Long productId) {
        try {
            repository.deleteById(productId);
            return true; // La eliminación tuvo éxito
        } catch (Exception e) {
            return false; // La eliminación falló
        }
    }

}
