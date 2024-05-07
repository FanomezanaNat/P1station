package com.nathan.p1sation.repository.model;

import java.time.Instant;

public class Transaction {
    private String id;
    private Instant date;
    private Fuel product;
    private TransactionType type;
    private float quantity;
}
