package com.demo.service;

import com.demo.dto.OrderDTO;
import com.demo.dto.OrderItemDTO;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.Product;
import com.demo.repository.OrderRepository;
import com.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Retrieve all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Retrieve an order by ID
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    // Create a new order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        return convertToDTO(orderRepository.save(order));
    }

    // Update an existing order
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setOrderDate(orderDTO.getOrderDate());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        existingOrder.setOrderItems(orderItems);

        return convertToDTO(orderRepository.save(existingOrder));
    }

    // Delete an order
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderItems(order.getOrderItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        return orderDTO;
    }

    private OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDTO.getQuantity());

        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        orderItem.setProduct(product);

        return orderItem;
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setProductId(orderItem.getProduct().getId());
        return orderItemDTO;
    }
}