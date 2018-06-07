package com.learningstuff.task_management_system.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotEmpty
    private String date;
    @NotEmpty
    private String startTime;
    @NotEmpty
    private String endTime;
    @NotEmpty
    private String description;
    @ManyToOne
    @JoinColumn(name = "User_Email")
    private User user;

    public Task(String date, String startTime, String endTime, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }
}
