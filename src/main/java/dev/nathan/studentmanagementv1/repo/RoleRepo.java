package dev.nathan.studentmanagementv1.repo;

import dev.nathan.studentmanagementv1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
