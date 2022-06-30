package com.marketboro.demo.order;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity(name = "user_order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {

    }

    public Order(String id, String userId, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.status = Status.ORDER_RECEIPTED;
        this.createdAt = ZonedDateTime.now();
        this.modifiedAt = ZonedDateTime.now();
        this.addItems(items);
    }

    public Order set(Order order) {
        this.userId = order.getUserId();
        this.status = order.getStatus();
        this.removeAllItems();
        this.addItems(order.getItems());
        this.setModifiedAtToCurrentTime();
        return this;
    }

    public Order addItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            this.addItem(item);
        }
        return this;
    }

    public Order addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
        this.setModifiedAtToCurrentTime();
        return this;

//        if (items.contains(item)) {
//            OrderItem temp = items.stream()
//                    .filter(tempItem -> tempItem.getItemId().equals(item.getItemId()))
//                    .findFirst()
//                    .get();
//
//            temp.setQuantity(item.getQuantity() + temp.getQuantity());
//        } else {
//            this.items.add(item);
//            item.setOrder(this);
//        }
    }

    public Order removeAllItems() {
        for (String itemId : items.stream().map(OrderItem::getItemId).collect(Collectors.toList())) {
            removeItem(itemId);
        }
        return this;
    }

    public Order removeItem(String itemId) {
        items.removeIf(orderItem -> orderItem.getItemId().equals(itemId));
        this.setModifiedAtToCurrentTime();
        return this;
    }

    public Order markDeliveryFinished() {

        if (this.status != Status.ORDER_RECEIPTED) {
            throw new IllegalStateException(String
                    .format("Cannot mark Order's delivery finished! Current status: %s.", this.status));
        }

        this.status = Status.DELIVERY_FINISHED;
        this.setModifiedAtToCurrentTime();
        return this;
    }

    public Order markOrderCanceled() {

        if (this.status != Status.ORDER_RECEIPTED) {
            throw new IllegalStateException(String
                    .format("Cannot mark Order's delivery finished! Current status: %s.", this.status));
        }

        this.status = Status.ORDER_CANCELED;
        this.setModifiedAtToCurrentTime();
        return this;
    }

    public Order setModifiedAtToCurrentTime() {
        this.modifiedAt = ZonedDateTime.now();
        return this;
    }

    public static enum Status {

        ORDER_RECEIPTED {
            @Override
            public String toString() {
                return "ORDER_RECEIPTED";
            }
        },

        DELIVERY_FINISHED {
            @Override
            public String toString() {
                return "DELIVERY_FINISHED";
            }
        },

        ORDER_CANCELED {
            @Override
            public String toString() {
                return "ORDER_CANCELED";
            }
        },
        ;
    }
}
