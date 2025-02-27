package org.example.authorization_practice.cookie;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    private String id;
    private String name;
    private String password;
}
