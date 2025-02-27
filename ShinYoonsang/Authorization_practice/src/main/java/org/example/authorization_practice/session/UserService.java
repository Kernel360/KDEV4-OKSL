package org.example.authorization_practice.session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void login(UserDTO userDTO, HttpSession httpSession) {
        String userName = userDTO.getName();
        String password = userDTO.getPassword();

        UserDTO loginUser = userRepository.findByNameAndPassword(userName, password)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));

        if (loginUser.getPassword().equals(password)) {
            httpSession.setAttribute("user", loginUser);

        } else {
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }
}
