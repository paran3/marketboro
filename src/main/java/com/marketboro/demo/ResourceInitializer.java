package com.marketboro.demo;

import com.marketboro.demo.item.Item;
import com.marketboro.demo.item.ItemRepository;
import com.marketboro.demo.order.Order;
import com.marketboro.demo.order.OrderItem;
import com.marketboro.demo.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResourceInitializer {

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    public ResourceInitializer(ItemRepository itemRepository, OrderRepository orderRepository) {

        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;

        Item i1 = new Item(1000L, "apple");
        Item i2 = new Item(1000L, "watermelon");
        Item i3 = new Item(1000L, "melon");
        Item i4 = new Item(1000L, "banana");
        Item i5 = new Item(1000L, "kiwi");
        Item i6 = new Item(1000L, "grape");
        Item i7 = new Item(1000L, "orange");
        Item i8 = new Item(1000L, "pineapple");
        Item i9 = new Item(1000L, "strawberry");
        Item i10 = new Item(1000L, "blueberry");
        Item i11 = new Item(1000L, "pear");
        Item i12 = new Item(1000L, "tomato");
        Item i13 = new Item("1", 1000L, "peach");


        Stream.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13)
                .forEach(this.itemRepository::save);

        List<Item> items = this.itemRepository.findAll();

        ArrayList<Order> orders = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            List<OrderItem> orderItems = items.stream()
                    .map(item -> new OrderItem(item.getId(), item.getPrice(), item.getName(), 1))
                    .collect(Collectors.toList());

            Order order = new Order(null, "kim", orderItems);
            orders.add(order);
        }

        this.orderRepository.saveAll(orders);
    }
}
