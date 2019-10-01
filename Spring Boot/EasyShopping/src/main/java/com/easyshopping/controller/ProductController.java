package com.easyshopping.controller;

import com.easyshopping.exception.ResourceNotFoundException;
import com.easyshopping.model.Product;
import com.easyshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("product")
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable(name = "id") long id){
        return productRepository.findById(id) .orElseThrow(()->new ResourceNotFoundException("Id","id",id));
    }

    @GetMapping("product/type/{type}")
    public List<Product> getByCategory(@PathVariable(name = "type") String type) throws Exception {
        return productRepository.findByType(type);
    }

    @PostMapping("product")
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("product/{id}")
    public Product editProduct(@PathVariable(name = "id") long id, @RequestBody Product product) {
        Product product1 = productRepository.findById(id) .orElseThrow(()->new ResourceNotFoundException("Id","id",id));
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setType(product.getType());
        product1.setUnit(product.getUnit());
        return productRepository.save(product1);
    }
}
