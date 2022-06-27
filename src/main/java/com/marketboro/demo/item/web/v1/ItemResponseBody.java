package com.marketboro.demo.item.web.v1;

import com.marketboro.demo.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class ItemResponseBody {

    private String id;
    private Long price;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    public ItemResponseBody(String id, Long price, String name, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ItemResponseBody of(Item item) {
        return new ItemResponseBody(item.getId(), item.getPrice(), item.getName(), item.getCreatedAt(), item.getModifiedAt());
    }
}
