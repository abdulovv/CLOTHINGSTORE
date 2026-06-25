package com.abdulovv.clothingstore.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems(Integer id){
        return cartItemRepository.findByUserId(id);
    }

    public CartItem saveCartItem(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }
}
