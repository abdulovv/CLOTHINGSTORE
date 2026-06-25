package com.abdulovv.clothingstore.orderItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems(Integer id){
        return orderItemRepository.findByOrderId(id);
    }

    public OrderItem saveOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
