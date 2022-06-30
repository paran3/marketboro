package com.marketboro.demo.order;

import com.marketboro.demo.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EventService eventService;

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
        String orderId = orderRepository.save(order).getId();
        eventService.publishAlertEvent(order.getUserId(), order.getStatus().toString());
        return orderId;
    }

    public void putOrder(Order order) throws Exception {
        orderRepository.putOrder(order);
    }

    public void removeOrder(String id) throws Exception {
        if (isOrderExists(id)) {
            Order order = getOrder(id).get();
            order = orderRepository.markOrderCanceled(order);
            eventService.publishAlertEvent(order.getUserId(), order.getStatus().toString());
        } else {
            throw new Exception("Order is not exists");
        }
    }

    public void removeItems(String orderId, List<String> itemIds) throws Exception {
        if (!isOrderExists(orderId)) {
            throw new Exception("Order is not exists");
        }
        Order order = getOrder(orderId).get();

        for (String itemId : itemIds) {
            orderRepository.removeOrderItem(order, itemId);
        }

    }

    public void removeItem(String orderId, String itemId) throws Exception {
        if (!isOrderExists(orderId)) {
            throw new Exception("Order is not exists");
        }
        Order order = getOrder(orderId).get();

        orderRepository.removeOrderItem(order, itemId);

    }

}
