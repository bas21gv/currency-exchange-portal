package com.maveric.thinknxt.stock.service;

import com.maveric.thinknxt.stock.model.Order;
import com.maveric.thinknxt.stock.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public boolean performUpdate(Long buyOrderId, Long sellOrderId, int amount) {
        Order buyOrder = orderRepository.findById(buyOrderId).orElseThrow();
        Order sellOrder = orderRepository.findById(sellOrderId).orElseThrow();
        int buyAvailableCount = buyOrder.getProductCount() - buyOrder.getRealizedCount();
        int sellAvailableCount = sellOrder.getProductCount() - sellOrder.getRealizedCount();
        return false;
    }
}
