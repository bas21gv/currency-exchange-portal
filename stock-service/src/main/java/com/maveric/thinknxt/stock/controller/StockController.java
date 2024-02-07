package com.maveric.thinknxt.stock.controller;

import com.maveric.thinknxt.stock.model.Order;
import com.maveric.thinknxt.stock.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/stock")
@RequiredArgsConstructor
@RestController
@Slf4j
public class StockController {

    private final OrderService orderService;

    @GetMapping("/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
