package com.demo.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private int quantity;
    private Long productId;
}