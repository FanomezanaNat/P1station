package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationProduct {
    private String id;
    private Instant date;
    private TransactionType type;
    private Product product;
    private float quantity;

    public OperationProduct(String id) {
        this.id = id;
    }

    public OperationProduct(TransactionType type, Product product, float quantity) {

        this.type = type;
        this.product = product;
        this.quantity = quantity;
    }
}
