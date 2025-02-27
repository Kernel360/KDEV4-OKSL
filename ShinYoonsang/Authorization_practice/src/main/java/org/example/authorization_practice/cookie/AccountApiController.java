package org.example.authorization_practice.cookie;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/cookie/account")
@RestController
public class AccountApiController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody UserDTO userDTO, HttpServletResponse httpServletResponse) {
        userService.login(userDTO, httpServletResponse);
    }
}
