package com.example.mockApp.controller;

import com.example.mockApp.service.DataBaseWorker;
import com.example.mockApp.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Random;

@RestController
@Validated
public class MockAppController {
    private static final Random random = new Random();

    @Autowired
    private DataBaseWorker dbWorker;

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
        try {
            return ResponseEntity.ok(dbWorker.selectUserByLogin(login));
        } catch (IllegalStateException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postInfo(@Valid @RequestBody User user){
        randomDelay();
        user.setDate(new Date());
        try {
            dbWorker.insertUser(user);
            return ResponseEntity.ok(user);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
