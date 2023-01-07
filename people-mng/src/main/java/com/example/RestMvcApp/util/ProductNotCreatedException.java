package com.example.RestMvcApp.util;

public class ProductNotCreatedException extends RuntimeException {
    public ProductNotCreatedException(String msg) {
        super(msg);
    }
}
