package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.Fuel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    private String id;
    private Fuel name;
    private int unitPrice;

    public ProductTemplate(String id) {
        this.id = id;
    }
}
