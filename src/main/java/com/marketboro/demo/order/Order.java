package com.marketboro.demo.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public void addItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            this.addItem(item);
        }
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
    }

    public void removeItem(String itemId) {
//        items.removeIf(orderItem -> orderItem.getItemId().equals(itemId));

        OrderItem item = items.stream()
                .filter(orderItem -> orderItem.getItemId().equals(itemId))
                .collect(Collectors.toList()).get(0);

        item.setOrder(null);
        this.items.remove(item);

    }

    public Order markDeliveryFinished() {

        if (this.status != Status.ORDER_RECEIPTED) {
            throw new IllegalStateException(String
                    .format("Cannot mark Order's delivery finished! Current status: %s.", this.status));
        }

        this.status = Status.DELIVERY_FINISHED;

        return this;
    }

    public Order markOrderCanceled() {

        if (this.status != Status.ORDER_RECEIPTED) {
            throw new IllegalStateException(String
                    .format("Cannot mark Order's delivery finished! Current status: %s.", this.status));
        }

        this.status = Status.ORDER_CANCELED;

        return this;
    }

    public static enum Status {

        ORDER_RECEIPTED,

        DELIVERY_FINISHED,

        ORDER_CANCELED;
    }
}
