package com.learningstuff.onlineaddressbook.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @Column(unique = true)
    @Email(message = "Input a valid email!")
    @NotNull(message = "Field must not be empty!")
    @Size(min = 1, message = "Field must not be empty!")
    private String email;
    @NotNull(message = "Field must not be empty!")
    @Size(min = 1, message = "Field must not be empty!")
    private String name;
    @NotNull(message = "Field must not be empty!")
    @Size(min = 5, message = "Minimum 5 digit!")
    private String password;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AddressBook> addressBooks;

    public User(String email, String name, String password, List<Role> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public User(User user) {
        this.email = user.email;
        this.name = user.name;
        this.password = user.password;
        this.roles = user.roles;
    }
}
