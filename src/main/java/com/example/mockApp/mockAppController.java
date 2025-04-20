package com.example.mockApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class mockAppController {
    @GetMapping("/get")
    public String getInfo(){
        return "{\"login\":\"Login1\",\"status\":\"ok\"}";
    }

    @PostMapping("/post")
    public String postInfo(@RequestBody User user){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return String.format("{\"login\":\"%s\",\"password\":\"%s\",\"date\":\"%s\"}",
                user.getLogin(), user.getPassword(), timeStamp);
    }
}
