package dev.nathan.studentmanagementv1.utility;

import dev.nathan.studentmanagementv1.entity.User;
import dev.nathan.studentmanagementv1.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GlobalUtility {

    private final UserRepo userRepo;

    public boolean checkExistUserByPhone(String phoneNumber) {
        User user = userRepo.getUserByPhoneNumber(phoneNumber);
        if(user!=null) {
            return true;
        }
        return false;
    }

}
