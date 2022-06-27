package com.marketboro.demo.item;

import com.marketboro.demo.item.web.v1.ItemResponseBody;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "price")
    private Long price;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

    public Item() {

    }

    public Item(String id, Long price, String name, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Item(String id, Long price, String name) {
        this(id, price, name, ZonedDateTime.now(), ZonedDateTime.now());
    }

}
