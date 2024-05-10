package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.model.exceptions.InsufficientCapacityException;
import com.hei.p1sation.model.exceptions.InsufficientStockException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

