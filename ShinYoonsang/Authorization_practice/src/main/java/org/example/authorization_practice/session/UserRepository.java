package org.example.authorization_practice.session;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<UserDTO> userList = new ArrayList<UserDTO>();

    @PostConstruct
    public void init() {
        userList.add(new UserDTO("홍길동", "1234"));
        userList.add(new UserDTO("김철수", "1234"));
        userList.add(new UserDTO("김유저", "1234"));
    }

    public Optional<UserDTO> findByNameAndPassword(String name, String password) {
        return userList.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }
}
