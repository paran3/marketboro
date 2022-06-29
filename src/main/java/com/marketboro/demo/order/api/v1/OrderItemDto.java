package com.marketboro.demo.order.api.v1;

import com.marketboro.demo.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private String itemId;
    private Long price;
    private String name;
    private int quantity;


    public OrderItem to() {
        return new OrderItem(this.itemId, this.price, this.name, this.quantity);
    }

    public static OrderItemDto of(OrderItem item) {
        return new OrderItemDto(item.getItemId(), item.getPrice(), item.getName(), item.getQuantity());
    }
}
