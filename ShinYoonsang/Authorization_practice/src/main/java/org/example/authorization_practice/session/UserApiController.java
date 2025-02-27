package org.example.authorization_practice.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserApiController {

    @GetMapping("/me")
    public UserDTO me(HttpSession httpSession) {
        Object user = httpSession.getAttribute("user");

        if (user != null) {
            return (UserDTO) user;
        }

        return null;
    }
}
