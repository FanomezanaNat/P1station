package com.hei.p1sation.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelStation {
    private String id;
    private String name;
    private String address;
    private Location location;
    private int fuelCapacity;
    private List<ProductTemplate> products;
}
