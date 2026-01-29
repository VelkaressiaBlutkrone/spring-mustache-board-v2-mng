package com.example.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Optional<User> userInfoByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

}
