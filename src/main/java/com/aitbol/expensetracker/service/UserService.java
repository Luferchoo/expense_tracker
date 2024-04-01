package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.UserDto;
import com.aitbol.expensetracker.model.entity.User;
import com.aitbol.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserByUsername(String username) {
        Optional<User> optional = this.userRepository.findById(username);
        if (optional.isPresent()) {
            return new UserDto(optional.get());
        } else {
            return null;
        }
    }
}


