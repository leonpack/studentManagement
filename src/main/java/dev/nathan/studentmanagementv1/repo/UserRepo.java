package dev.nathan.studentmanagementv1.repo;

import dev.nathan.studentmanagementv1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@Repository
@CrossOrigin
public interface UserRepo extends JpaRepository<User, UUID> {

    User getUserByPhoneNumber(String phoneNumber);

}
