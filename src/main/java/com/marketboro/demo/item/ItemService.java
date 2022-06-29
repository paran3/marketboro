package com.marketboro.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItem(String id) {
        return itemRepository.findById(id);
    }

    public String createItem(Item item) {
        return itemRepository.save(item).getId();
    }

    public String putItem(Item item) {
        item.setModifiedAt(ZonedDateTime.now());
        return itemRepository.save(item).getId();
    }

    public void removeItem(String id) {
        itemRepository.deleteById(id);
    }
}
