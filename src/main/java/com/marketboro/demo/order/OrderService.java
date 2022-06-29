package com.marketboro.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public boolean isOrderExists(String id) {
        return orderRepository.existsById(id);
    }

    public Optional<Order> getOrder(String id) {

        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public String createOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    public String putOrder(Order order) {
        order.setModifiedAt(ZonedDateTime.now());
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void removeOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void removeItems(String orderId, List<String> itemIds) throws Exception {
        if (!isOrderExists(orderId)) {
            throw new Exception("Order is not exists");
        }
        Order order = getOrder(orderId).get();

        for (String itemId : itemIds) {
            removeItem(order, itemId);
        }

    }

    @Transactional
    public void removeItem(String orderId, String itemId) throws Exception {
        if (!isOrderExists(orderId)) {
            throw new Exception("Order is not exists");
        }
        Order order = getOrder(orderId).get();

        removeItem(order, itemId);

    }

    private void removeItem(Order order, String itemId) {
        order.removeItem(itemId);
    }
}
