package org.example.authorization_practice.cookie;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {

    private List<UserDTO> userList = new ArrayList<UserDTO>();

    @PostConstruct
    public void init() {
        userList.add(new UserDTO(UUID.randomUUID().toString(),"홍길동", "1234"));
        userList.add(new UserDTO(UUID.randomUUID().toString(), "김철수", "1234"));
        userList.add(new UserDTO(UUID.randomUUID().toString(), "김유저", "1234"));
    }

    public Optional<UserDTO> findByName(String name, String password) {
        return userList.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }

    public Optional<UserDTO> findById(String id) {
        return userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}
