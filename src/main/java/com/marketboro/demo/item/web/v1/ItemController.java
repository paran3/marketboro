package com.marketboro.demo.item.web.v1;

import com.marketboro.demo.item.Item;
import com.marketboro.demo.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "items", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllItems() {
        List<Item> items = itemService.getAllItems();
        if (items.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                items.stream()
                        .map(item -> ItemResponseBody.of(item))
                        .collect(Collectors.toList())
        );
    }

    @RequestMapping(path = "items/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getItem(@PathVariable String id) {
        Optional<Item> item = itemService.getItem(id);
        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ItemResponseBody.of(item.get()));
    }

    @RequestMapping(path = "items", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createItem(@RequestBody ItemRequestBody requestBody) {
        if (requestBody.postValidation() == false) {
            return ResponseEntity.badRequest().build();
        }

        String id = itemService.createItem(requestBody.to());

        return ResponseEntity.created(URI.create(id)).build();
    }

    @RequestMapping(path = "items/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity putItem(@PathVariable String id, @RequestBody ItemRequestBody requestBody) {
        if (requestBody.putValidation() == false) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Item> item = itemService.getItem(id);

        if (item.isPresent()) {
            itemService.putItem(requestBody.to(id));
            return ResponseEntity.ok().build();
        } else {
            String itemId = itemService.createItem(requestBody.to());
            return ResponseEntity.created(URI.create(id)).build();
        }
    }

}
