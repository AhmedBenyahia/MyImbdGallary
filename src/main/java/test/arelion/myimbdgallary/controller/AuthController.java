package test.arelion.myimbdgallary.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import test.arelion.myimbdgallary.model.dto.UserDTO;
import test.arelion.myimbdgallary.service.AuthService;

import java.util.List;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return authService.createUser(userDTO);
    }

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return authService.getAllUser();
    }

}
