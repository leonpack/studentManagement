package dev.nathan.studentmanagementv1.controller;

import dev.nathan.studentmanagementv1.dto.LoginDTO;
import dev.nathan.studentmanagementv1.dto.requestDTO.UserRequestDTO;
import dev.nathan.studentmanagementv1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<?> addNewUsers(@RequestBody UserRequestDTO dto) {

        return new ResponseEntity<>(userService.saveUser(dto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginByPhoneNumber(@RequestBody LoginDTO dto) {
        return new ResponseEntity<>(userService.loginByPhone(dto), HttpStatusCode.valueOf(200));
    }

}
