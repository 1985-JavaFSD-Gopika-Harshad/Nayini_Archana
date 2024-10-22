package com.demo.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int stockQuantity;
    private Long categoryId;
    private Long supplierId;
}