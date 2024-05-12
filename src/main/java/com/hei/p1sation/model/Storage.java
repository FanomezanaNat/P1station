package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.Fuel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private String id;
    private float value;
    private FuelStation fuelStation;
    private Fuel product;
    private Instant last_updated;

    public Storage(float value, FuelStation fuelStation, Fuel product) {
        this.value = value;
        this.fuelStation = fuelStation;
        this.product = product;
    }
}
