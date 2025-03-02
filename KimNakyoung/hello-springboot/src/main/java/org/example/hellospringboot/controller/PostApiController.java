package org.example.hellospringboot.controller;

import org.example.hellospringboot.model.BookRequest;
import org.example.hellospringboot.model.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostApiController {


    @PostMapping("/post")
    public String post(
            @RequestBody BookRequest bookRequest

    ){
        System.out.println(bookRequest);
        return bookRequest.toString();

    }

    @PostMapping("/user")
    public UserRequest User(
            @RequestBody
            UserRequest userRequest
    ){
        System.out.println(userRequest);
        return userRequest;

    }
}
