package com.marketboro.demo.order.api.v1;

import com.marketboro.demo.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestBody {

    private String userId;
    private List<OrderItemDto> items;

    public Order to() {
        return new Order(null, this.userId,
                items.stream().map(OrderItemDto::to).collect(Collectors.toList())
        );
    }

    public Order toWithId(String id) {
        return new Order(id, this.userId,
                items.stream().map(OrderItemDto::to).collect(Collectors.toList())
        );
    }

}
