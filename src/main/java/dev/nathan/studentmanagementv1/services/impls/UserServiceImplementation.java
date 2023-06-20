package dev.nathan.studentmanagementv1.services.impls;

import dev.nathan.studentmanagementv1.dto.LoginDTO;
import dev.nathan.studentmanagementv1.dto.requestDTO.UserRequestDTO;
import dev.nathan.studentmanagementv1.dto.responseDTO.UserResponseDTO;
import dev.nathan.studentmanagementv1.entity.Role;
import dev.nathan.studentmanagementv1.entity.User;
import dev.nathan.studentmanagementv1.repo.RoleRepo;
import dev.nathan.studentmanagementv1.repo.UserRepo;
import dev.nathan.studentmanagementv1.services.UserService;
import dev.nathan.studentmanagementv1.utility.GlobalUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;
    private final GlobalUtility utility;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll().stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(UUID id) {
        Optional<User> result = userRepo.findById(id);
        User user = null;
        if(result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("User not found");
        }
        //convert return value into DTO
        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
        return dto;
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO user) {
        //create new instance for User
        User savedUser = new User();


        //Located the right role
        Optional<Role> result = roleRepo.findById(user.getTheRole());
        Role role = null;
        if(result.isPresent()){
            role = result.get();
        } else {
            throw new RuntimeException("Role id not found");
        }

        //check phoneNumber exist
        if(utility.checkExistUserByPhone(user.getPhoneNumber())){
            throw new RuntimeException("This phone number is already exist");
        }

        //set value for the new User
        savedUser.setUserName(user.getUserFullName());
        savedUser.setPhoneNumber(user.getPhoneNumber());
        savedUser.setTheRole(role);

        //hashing password with bcrypt
        String encodedPass = encoder.encode(user.getPassword());
        savedUser.setPassword(encodedPass);

        User finalUser = userRepo.save(savedUser);
        UserResponseDTO dto = modelMapper.map(finalUser, UserResponseDTO.class);

        return dto;
    }

    @Override
    public UserResponseDTO loginByPhone(LoginDTO credential) {
        //check user
        if(!utility.checkExistUserByPhone(credential.getPhoneNumber())){
            throw new RuntimeException("User with this phone number is not found!");
        }
        User user = userRepo.getUserByPhoneNumber(credential.getPhoneNumber());
        boolean checkingPassword = encoder.matches(credential.getPassword(), user.getPassword());
        if(!checkingPassword) {
            throw new RuntimeException("Wrong password");
        }
        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
        return dto;
    }

    @Override
    public boolean deleteUser(UUID id) {
        Optional<User> result = userRepo.findById(id);
        User user = null;
        if(result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("User not found");
        }
        userRepo.delete(user);
        return true;
    }

}
