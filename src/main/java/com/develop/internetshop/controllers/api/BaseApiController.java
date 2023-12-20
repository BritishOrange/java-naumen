package com.develop.internetshop.controllers.api;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * BaseRestController
 */
public class BaseApiController<T, ID> {
    private final CrudRepository<T, ID> repository;

    public BaseApiController(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<T> getProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<T> getProduct(@PathVariable ID id) {
        return repository.findById(id);
    }

    @PostMapping
    public T postProduct(@RequestBody T entity) {
        repository.save(entity);
        return entity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> putProduct(@PathVariable ID id, @RequestBody T newProduct) {
        return (repository.existsById(id)) ?
            ResponseEntity.status(HttpStatus.OK).body(newProduct) :
            ResponseEntity.status(HttpStatus.CREATED).body(postProduct(newProduct));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable ID id) {
        if (repository.existsById(id)) 
            repository.deleteById(id);
    }
    
}
