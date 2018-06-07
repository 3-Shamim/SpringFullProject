package com.learningstuff.task_management_system.Repository;

import com.learningstuff.task_management_system.Model.Task;
import com.learningstuff.task_management_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);
}
