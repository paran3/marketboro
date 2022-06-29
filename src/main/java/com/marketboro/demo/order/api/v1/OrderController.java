package com.marketboro.demo.order.api.v1;

import com.marketboro.demo.order.Order;
import com.marketboro.demo.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "orders", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.getAllOrders();

        if (orders.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        List<OrderResponseBody> response = orders.stream().map(order -> OrderResponseBody.of(order)).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "orders/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOrder(@PathVariable String orderId) {
        Optional<Order> order = orderService.getOrder(orderId);

        if (order.isPresent() == false) {
            return ResponseEntity.notFound().build();
        }

        OrderResponseBody response = OrderResponseBody.of(order.get());

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "orders", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createOrder(@RequestBody OrderRequestBody requestBody) {
        String orderId = orderService.createOrder(requestBody.to());

        return ResponseEntity.created(URI.create(orderId)).build();
    }

    @RequestMapping(path = "orders/{orderId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity putOrder(@PathVariable String orderId, @RequestBody OrderRequestBody requestBody) {
        if (orderService.isOrderExists(orderId)) {
            orderService.putOrder(requestBody.toWithId(orderId));
            return ResponseEntity.ok().build();
        } else {
            orderId = orderService.createOrder(requestBody.toWithId(orderId));
            return ResponseEntity.created(URI.create(orderId)).build();
        }
    }

    @RequestMapping(path = "orders/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeOrder(@PathVariable String orderId) {
        if (!orderService.isOrderExists(orderId)) {
            return ResponseEntity.notFound().build();
        }

        orderService.removeOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "orders/{orderId}/items/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeItem(@PathVariable String orderId, @PathVariable String itemId) {
        if (orderService.isOrderExists(orderId) == false) {
            return ResponseEntity.notFound().build();
        }

        try {
            orderService.removeItem(orderId, itemId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
