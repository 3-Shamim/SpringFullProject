package com.learningstuff.onlineaddressbook.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "native", strategy = "native")
    private int roleId;
    private String role;

    public Role(String role) {
        this.role = role;
    }
}
