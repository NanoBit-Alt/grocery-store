package com.market.app.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.market.app.model.Item;
import com.market.app.repository.ItemRepository;

@ApplicationScoped
public class ItemUseCase {

    private final ItemRepository itemRepository;

    @Inject
    public ItemUseCase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.listAll();
    }

    public Item findById(long id) {
        return itemRepository.findById(id);
    }

    public Item create(Item item) {
        itemRepository.persist(item);
        return item;
    }

    public String update(Item item) {
        if (itemRepository.findByIdOptional(item.getId()).isPresent()) {
            Item nObj = itemRepository.findById(item.getId());
            nObj.setName(item.getName());
            nObj.setPrice(item.getPrice());
            nObj.setDescription(item.getDescription());
            itemRepository.persist(nObj);
            return "item updated";
        } else return "item does not exists";
    }

    public String deleteById(long id) {
        if (itemRepository.findByIdOptional(id).isPresent()) {
            itemRepository.deleteById(id);
            return "item deleted";
        } else return "item does not exists";
    }
}