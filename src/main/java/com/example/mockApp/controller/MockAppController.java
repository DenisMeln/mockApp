package com.example.mockApp.controller;

import com.example.mockApp.service.DataBaseWorker;
import com.example.mockApp.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

@RestController
@Validated
public class MockAppController {
    private static final Random random = new Random();

    private final DataBaseWorker dbWorker;

    public MockAppController(DataBaseWorker dbWorker) {
        this.dbWorker = dbWorker;
    }
    private void randomDelay() {
        int delay = 1000 + random.nextInt(1000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Поток был прерван во время выполнения", ex);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getInfo(@RequestParam String login){
        randomDelay();
        User user = dbWorker.selectUserByLogin(login);
        if (user == null) {
            throw new IllegalStateException("Пользователь с логином '" + login + "' не найден");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/post")
    public ResponseEntity<?> postInfo(@Valid @RequestBody User user){
        randomDelay();
        user.setDate(new Date());
        int result = dbWorker.insertUser(user);
        if (result <= 0) {
            throw new IllegalStateException("Ошибка при добавлении пользователя");
        }
        return ResponseEntity.ok(user);
    }
}
