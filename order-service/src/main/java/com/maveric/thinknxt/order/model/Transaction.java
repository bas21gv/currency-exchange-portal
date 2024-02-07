package com.maveric.thinknxt.order.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long buyOrderId;
    private Long sellOrderId;
    private Integer amount;
    private Integer price;
    private LocalDateTime creationTime;
}
