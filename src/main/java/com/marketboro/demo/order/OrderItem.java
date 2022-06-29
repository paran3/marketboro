package com.marketboro.demo.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@IdClass(OrderItemId.class)
public class OrderItem {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "price")
    private Long price;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;

    public OrderItem() {
    }

    public OrderItem(String itemId, Long price, String name, int quantity) {
        this.itemId = itemId;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public OrderItem increaseAmount() {
        this.quantity++;
        return this;
    }
}
