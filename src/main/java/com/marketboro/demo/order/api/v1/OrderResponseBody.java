package com.marketboro.demo.order.api.v1;

import com.marketboro.demo.order.Order;
import com.marketboro.demo.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseBody {

    private String id;
    private String userId;
    private Order.Status status;
    private String createdAt;
    private String modifiedAt;
    private List<OrderItemDto> items;

    public static OrderResponseBody of(Order order) {
        return new OrderResponseBody(order.getId(),
                order.getUserId(),
                order.getStatus(),
                DateTimeUtil.to(order.getCreatedAt()),
                DateTimeUtil.to(order.getModifiedAt()),
                order.getItems().stream().map(item -> OrderItemDto.of(item)).collect(Collectors.toList())
        );
    }
}

