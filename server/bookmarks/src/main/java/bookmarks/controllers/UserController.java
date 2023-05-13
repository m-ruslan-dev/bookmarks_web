package bookmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bookmarks.DTO.UserDTO;
import bookmarks.builders.UserBuilder;
import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.exceptions.RoleNotFoundException;
import bookmarks.models.Role;
import bookmarks.models.User;
import bookmarks.services.RoleService;
import bookmarks.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserBuilder userBuilder;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            // Encode password
            final String password = userDTO.getPassword();
            final String encodedPassword = passwordEncoder.encode(password);

            // Build user entity
            final String username = userDTO.getUsername();
            final String email = userDTO.getEmail();
            final Role role = roleService.getRoleByName("user");
            final User user = userBuilder.buildUser(username, encodedPassword, email, role);

            // Add user to the database
            userService.saveUser(user);

            return ResponseEntity.ok().build();
        } catch (EmptyFieldsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    };
}
