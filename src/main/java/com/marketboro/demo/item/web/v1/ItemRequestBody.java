package com.marketboro.demo.item.web.v1;

import com.marketboro.demo.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class ItemRequestBody {

    @Nullable
    private String id;
    @Nullable
    private Long price;
    @Nullable
    private String name;

    public ItemRequestBody(String id, Long price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Item to(String id) {
        return new Item(id, this.price, this.name);
    }

    public Item to() {
        return new Item(this.id, this.price, this.name);
    }

    public boolean postValidation() {
        if (price == null || name == null) {
            return false;
        }

        return true;
    }

    public boolean putValidation() {
        if (price == null && name == null) {
            return false;
        }

        return true;
    }
}
