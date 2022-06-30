package com.marketboro.demo.order;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Transactional
    default Order removeOrderItem(Order order, String itemId) {
        return save(order.removeItem(itemId));
    }

    @Transactional
    default Order addOrderItem(Order order, OrderItem item) {
        return save(order.addItem(item));
    }

    @Transactional
    default Order addOrderItems(Order order, List<OrderItem> items) {
        return save(order.addItems(items));
    }

    @Transactional
    default Order putOrder(Order order) {
        Order existOrder = findById(order.getId()).get();
        existOrder.set(order);
        return save(existOrder);
    }

    @Transactional
    default Order markDeliveryFinished(Order order) {
        return save(order.markDeliveryFinished());
    }

    @Transactional
    default Order markOrderCanceled(Order order) {
        return save(order.markOrderCanceled());
    }
}
