package com.market.app.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.market.app.model.Order;
import com.market.app.repository.OrderRepository;

@ApplicationScoped
public class OrderService {

    private OrderRepository orderRepository;

    @Inject
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        orderRepository.persist(order);
        return order;
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getUserOrders(long id) {
        return orderRepository.getUserOrders(id);
    }

}