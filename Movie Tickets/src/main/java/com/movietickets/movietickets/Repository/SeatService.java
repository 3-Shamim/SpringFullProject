package com.movietickets.movietickets.Repository;

import com.movietickets.movietickets.Model.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatService {

    private List<Seat> seats;

    public List<Seat> InitializeSeat(){

        seats = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
        {
            seats.add(new Seat(i,"G", false) );
        }
        return seats;
    }

}
