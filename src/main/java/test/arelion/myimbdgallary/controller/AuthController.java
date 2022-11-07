package test.arelion.myimbdgallary.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import test.arelion.myimbdgallary.model.dto.UserDTO;
import test.arelion.myimbdgallary.service.AuthService;

import java.util.List;


/**
 * The {@link Auth controller } class.
 *
 * @author AhmedBenyahia
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    /**
     * Create user and save it.
     * The method also generate an API key for the use depending on the Pricing Plan.
     *
     * @param userDTO the user to save
     * @return the user
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return authService.createUser(userDTO);
    }

    /**
     * Gets all users in DB.
     *
     * @return List of users
     */
    @GetMapping("/")
    public List<UserDTO> getAll() {
        return authService.getAllUser();
    }

}
