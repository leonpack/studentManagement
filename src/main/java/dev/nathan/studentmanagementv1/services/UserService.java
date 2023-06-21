package dev.nathan.studentmanagementv1.services;

import dev.nathan.studentmanagementv1.dto.LoginDTO;
import dev.nathan.studentmanagementv1.dto.requestDTO.UserRequestDTO;
import dev.nathan.studentmanagementv1.dto.responseDTO.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(UUID id);

    UserResponseDTO saveUser(UserRequestDTO user);

    UserResponseDTO loginByPhone(LoginDTO credential);

    boolean deleteUser(UUID id);

}
