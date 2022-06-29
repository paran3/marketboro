package com.marketboro.demo.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {

    private String itemId;
    private String order;
}
