package com.market.app.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.market.app.model.Item;

@ApplicationScoped
public class ItemService {

    private ItemUseCase itemUseCase;

    @Inject
    public ItemService(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    public Item getOne(long id) {
        return itemUseCase.findById(id);
    }

    public List<Item> getAll() {
        return itemUseCase.findAll();
    }

    @Transactional
    public Item create(Item item) {
        return itemUseCase.create(item);
    }

    @Transactional
    public String update(Item item) {
        return itemUseCase.update(item);
    }

    @Transactional
    public String deleteById(long id) {
        itemUseCase.deleteById(id);
        return "product deleted";
    }
}