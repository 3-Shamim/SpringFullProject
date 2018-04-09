package com.movietickets.movietickets.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String name;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String length;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String director;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String actor;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String description;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String date;

}
