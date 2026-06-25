package com.abdulovv.clothingstore.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/cart")
@RestController
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/{user_id}")
    public List<CartItem> getAllCartItems(@PathVariable("user_id") Integer id){
        return cartItemService.getAllCartItems(id);
    }

    @PostMapping
    public CartItem saveCartItem(@RequestBody CartItem cartItem){
        return cartItemService.saveCartItem(cartItem);
    }
}
