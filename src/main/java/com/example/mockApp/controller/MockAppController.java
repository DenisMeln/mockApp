package com.example.mockApp.controller;

import com.example.mockApp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MockAppController {
    private static final int delay = 1000;

    @GetMapping("/get")
    public ResponseEntity<String> getInfo() throws InterruptedException{
        Thread.sleep(delay);
        return ResponseEntity.ok("{\"login\":\"Login1\",\"status\":\"ok\"}");
    }

    @PostMapping("/post")
    public ResponseEntity<User> postInfo(@Valid @RequestBody User user) throws InterruptedException{
        Thread.sleep(delay);
        User responseUser = new User(user.getLogin(), user.getPassword());
        return ResponseEntity.ok(responseUser);
    }
}
