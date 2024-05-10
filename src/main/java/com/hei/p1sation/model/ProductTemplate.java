package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.Fuel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    private int id;
    private Fuel name;
    private int unitPrice;
}