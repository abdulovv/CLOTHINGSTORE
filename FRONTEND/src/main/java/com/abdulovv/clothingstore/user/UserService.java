package com.abdulovv.clothingstore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

}
