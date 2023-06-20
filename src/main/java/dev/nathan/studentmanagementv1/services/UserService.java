package dev.nathan.studentmanagementv1.services;

import dev.nathan.studentmanagementv1.dto.LoginDTO;
import dev.nathan.studentmanagementv1.dto.requestDTO.UserRequestDTO;
import dev.nathan.studentmanagementv1.dto.responseDTO.UserResponseDTO;
import dev.nathan.studentmanagementv1.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(UUID id);

    UserResponseDTO saveUser(UserRequestDTO user);

    UserResponseDTO loginByPhone(LoginDTO credential);

    boolean deleteUser(UUID id);

}
