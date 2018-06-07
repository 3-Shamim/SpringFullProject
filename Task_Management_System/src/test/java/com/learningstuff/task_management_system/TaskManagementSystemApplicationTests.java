package com.learningstuff.task_management_system;

import com.learningstuff.task_management_system.Model.Task;
import com.learningstuff.task_management_system.Model.User;
import com.learningstuff.task_management_system.Service.TaskService;
import com.learningstuff.task_management_system.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagementSystemApplicationTests {

    @Autowired
	private UserService userService;

    @Autowired
    private TaskService taskService;

    @Before
    public void initDB(){
        userService.createUser(new User("testUser@mail.com","testUser","123456"));
        userService.createAdmin(new User("testAdmin@mail.com","testAdmin","123456"));

        Task task = new Task("12/02/2018", "00.11", "11.11","You need to work today");
        User user = userService.findOne("testUser@mail.com");
        taskService.addTask(task, user);
    }

	@Test
	public void contextLoads() {
        User user = userService.findOne("testUser@mail.com");
        assertNotNull(user);
        User admin = userService.findOne("testAdmin@mail.com");
        assertEquals(admin.getEmail(), "testAdmin@mail.com");
    }

    public void testTask()
    {
        User user = userService.findOne("testUser@mail.com");
        List<Task> tasks = taskService.findUserTask(user);

        assertNotNull(tasks);
    }

}
