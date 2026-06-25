package com.abdulovv.clothingstore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable(name = "user_id") Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
