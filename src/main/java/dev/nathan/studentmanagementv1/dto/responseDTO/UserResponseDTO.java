package dev.nathan.studentmanagementv1.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.nathan.studentmanagementv1.entity.Role;
import lombok.Data;

@Data
public class UserResponseDTO {

    private String name;
    private String phoneNumber;
    @JsonIgnoreProperties("userList")
    private Role theRole;

}
