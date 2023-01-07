package com.example.RestMvcApp.Controllers;

import com.example.RestMvcApp.dto.ProductDTO;
import com.example.RestMvcApp.models.Product;
import com.example.RestMvcApp.services.ProductService;
import com.example.RestMvcApp.util.ErrorResponse;
import com.example.RestMvcApp.util.PersonNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<ProductDTO> getProduct() {
        return productService.findAll().stream().map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return convertToProductDTO(productService.findById(id));
    }

    @GetMapping("/name={name}")
    public List<ProductDTO> getProductByName(@PathVariable String name) {
        return productService.findByName(name).stream().map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
        errorsHandler(bindingResult);
        productService.save(convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    private ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(PersonNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private static void errorsHandler(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append((error.getDefaultMessage())).append("; ");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
    }
}
