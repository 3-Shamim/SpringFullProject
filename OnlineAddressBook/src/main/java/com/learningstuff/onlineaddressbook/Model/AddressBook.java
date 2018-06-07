package com.learningstuff.onlineaddressbook.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotNull(message = "Field must not be empty! ")
    @Size(min = 1, message = "Field must not be empty! ")
    private String name;
    @NotNull(message = "Field must not be empty! ")
    @Size(min = 1, message = "Field must not be empty! ")
    private String homeAddress;
    @NotNull(message = "Field must not be empty! ")
    @Size(min = 1, message = "Field must not be empty! ")
    private String currentAddress;
    private String number;
    @Email(message = "Input a valid email!")
    private String email;
    private String aboutPerson;
    @ManyToOne
    @JoinColumn(name = "USER_EMAIL")
    private User  user;

    public AddressBook(String name, String homeAddress, String currentAddress, String number, String email, String aboutPerson) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.currentAddress = currentAddress;
        this.number = number;
        this.email = email;
        this.aboutPerson = aboutPerson;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", aboutPerson='" + aboutPerson + '\'' +
                '}';
    }
}
