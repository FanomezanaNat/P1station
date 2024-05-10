package com.hei.p1sation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelStation {
    private String id;
    private String address;
    private Location location;

    public FuelStation(String id) {
        this.id = id;
    }



}

