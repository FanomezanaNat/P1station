package com.hei.p1sation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private float evaporationRate;
    private FuelStation fuelStation;
    private ProductTemplate product;

    public Product(String id) {
        this.id = id;
    }

    public Product(FuelStation fuelStation,float evaporationRate, ProductTemplate product) {
        this.fuelStation = fuelStation;
        this.product = product;
        this.evaporationRate=evaporationRate;
    }
}
