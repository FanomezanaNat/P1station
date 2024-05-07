package com.hei.p1sation.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;
    private Instant date;
    private Fuel product;
    private TransactionType type;
    private float quantity;
}
