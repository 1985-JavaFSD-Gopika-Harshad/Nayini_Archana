package com.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String orderDate;
    private List<OrderItemDTO> orderItems;
}