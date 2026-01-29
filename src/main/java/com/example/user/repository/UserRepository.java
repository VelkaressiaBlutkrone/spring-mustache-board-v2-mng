package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByName(String name);
}
