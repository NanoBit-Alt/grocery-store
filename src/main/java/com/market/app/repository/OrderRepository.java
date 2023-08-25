package com.market.app.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.market.app.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public List<Order> getUserOrders(long id) {
        return find("user_id", id).list();
    }
}