package com.crealo.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer id) {
        super("Product id: " + id + " was not found. ");
    }
}
