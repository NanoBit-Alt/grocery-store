package com.market.app.repository;

import javax.enterprise.context.ApplicationScoped;

import com.market.app.model.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}