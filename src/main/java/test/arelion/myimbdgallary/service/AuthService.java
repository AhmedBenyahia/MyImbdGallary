package test.arelion.myimbdgallary.service;

import test.arelion.myimbdgallary.model.dto.UserDTO;

import java.util.List;

public interface AuthService {
    UserDTO createUser(UserDTO user);

    List<UserDTO> getAllUser();

    String getUserApiKey(String username);
}
