package com.hei.p1sation.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    private int id;
    private Fuel name;
    private float unitPrice;
    private float evaporationRate;
}
