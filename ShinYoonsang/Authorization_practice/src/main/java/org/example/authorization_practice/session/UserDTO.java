package org.example.authorization_practice.session;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {

    private String name;
    private String password;
}
