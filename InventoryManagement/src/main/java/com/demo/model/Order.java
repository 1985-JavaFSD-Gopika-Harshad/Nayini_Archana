package com.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "`order`") 
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
