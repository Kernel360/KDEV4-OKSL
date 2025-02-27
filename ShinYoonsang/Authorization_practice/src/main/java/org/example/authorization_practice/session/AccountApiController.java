package org.example.authorization_practice.session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/account")
@RestController
public class AccountApiController {


    private final UserService userService;
    @PostMapping("/login")
    public void login(@RequestBody UserDTO userDTO, HttpSession httpSession) {
        userService.login(userDTO, httpSession);
    }
}
