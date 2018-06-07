package com.learningstuff.task_management_system.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String name;
    @Size(min = 4)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Roles", joinColumns = {
            @JoinColumn(name = "User_Email", referencedColumnName = "email")
    }, inverseJoinColumns = @JoinColumn(name = "Role_Name", referencedColumnName = "name"))
    private List<Role> roles;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
