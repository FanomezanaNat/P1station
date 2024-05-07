package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {
    private String id;
    private FuelStation station;
    private TransactionType type;
    private List<ProductTemplate> products;
}
