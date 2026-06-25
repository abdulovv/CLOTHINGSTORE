package com.abdulovv.clothingstore.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/orders")
@RestController
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{order_id}")
    public Order getOrderById(@PathVariable(name = "order_id") Integer id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }


}
