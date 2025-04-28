package com.example.mockApp.controller;

import com.example.mockApp.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@Validated
public class MockAppController {
    private static final Random random = new Random();

    private void randomDelay() throws InterruptedException {
        int delay = 1000 + random.nextInt(1000);
        Thread.sleep(delay);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getInfo() throws InterruptedException{
        randomDelay();
        return ResponseEntity.ok("{\"login\":\"Login1\",\"status\":\"ok\"}");
    }

    @PostMapping("/post")
    public ResponseEntity<?> postInfo(@Valid @RequestBody User user) throws InterruptedException{
        randomDelay();
        user.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return ResponseEntity.ok(user);
    }
}
