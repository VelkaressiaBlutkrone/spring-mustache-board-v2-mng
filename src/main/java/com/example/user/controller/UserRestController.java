package com.example.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.entity.User;
import com.example.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;

    @PostMapping("/user/info")
    public ResponseEntity<User> getUserInfoString(@RequestBody String userEmail) {
        ResponseEntity<User> response = service.userInfoByEmail(userEmail)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));

        return response;
    }

}
