package org.example.filter.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.interceptor.OpenApi;
import org.example.filter.model.UserRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api")
@RestController
public class UserApiController {

    @OpenApi
    @PostMapping("/")
    public UserRequest api(@RequestBody UserRequest userRequest) {
      log.info("{}", userRequest);
      return userRequest;
    }

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }
}
