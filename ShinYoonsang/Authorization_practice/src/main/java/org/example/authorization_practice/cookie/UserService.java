package org.example.authorization_practice.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void login(UserDTO userDTO, HttpServletResponse httpServletResponse) {
        String userName = userDTO.getName();
        String password = userDTO.getPassword();

        UserDTO loginUser = userRepository.findByName(userName, password)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));

        if (loginUser.getPassword().equals(password)) {
            Cookie cookie = new Cookie("user", userDTO.getId());
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        } else {
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }
}
