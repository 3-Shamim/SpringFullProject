package com.learningstuff.task_management_system.Repository;

import com.learningstuff.task_management_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
