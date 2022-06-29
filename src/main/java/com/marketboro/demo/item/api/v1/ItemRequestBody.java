package com.marketboro.demo.item.api.v1;

import com.marketboro.demo.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestBody {

    private Long price;
    private String name;

    public Item toWithId(String id) {
        return new Item(id, this.price, this.name);
    }

    public Item to() {
        return new Item(this.price, this.name);
    }

}
