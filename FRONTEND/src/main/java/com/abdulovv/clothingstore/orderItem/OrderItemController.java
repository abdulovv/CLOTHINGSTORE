package com.abdulovv.clothingstore.orderItem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/orders")
@RestController
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{order_id}/items")
    public List<OrderItem> getAllOrderItems(@PathVariable(name = "order_id") Integer id){
        return orderItemService.getAllOrderItems(id);
    }

    @PostMapping("/{order_id}/items")
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.saveOrderItem(orderItem);
    }


}

