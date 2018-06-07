package com.movietickets.movietickets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Seat {

    private int seatNumber;
    private String seatClass;
    private boolean isSold;

}
