package dev.nathan.studentmanagementv1.dto.requestDTO;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String userFullName;
    private String password;
    private String phoneNumber;
    private int theRole;

}
