package it.pilates.controller;

import it.pilates.dto.UserResponse;
import it.pilates.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping(params = "email")
    public UserResponse findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}