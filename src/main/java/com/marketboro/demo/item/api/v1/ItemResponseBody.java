package com.marketboro.demo.item.api.v1;

import com.marketboro.demo.item.Item;
import com.marketboro.demo.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseBody {

    private String id;
    private Long price;
    private String name;
    private String createdAt;
    private String modifiedAt;

    public static ItemResponseBody of(Item item) {
        return new ItemResponseBody(item.getId(),
                item.getPrice(),
                item.getName(),
                DateTimeUtil.to(item.getCreatedAt()),
                DateTimeUtil.to(item.getModifiedAt())
        );
    }
}
