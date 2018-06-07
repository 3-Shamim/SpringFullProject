package com.learningstuff.task_management_system.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Role {

    @Id
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
