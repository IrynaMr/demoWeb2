package com.example.demoWeb.service;

import com.example.demoWeb.model.Order;
import com.example.demoWeb.model.OrderStatus;
import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.OrderRepository;
import com.example.demoWeb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        return orderRepository.save(order);
    }

    public List<Order> getOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}