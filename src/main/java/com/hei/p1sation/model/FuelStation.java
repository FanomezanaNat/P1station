package com.hei.p1sation.model;

import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.model.exceptions.InsufficientCapacityException;
import com.hei.p1sation.model.exceptions.InsufficientStockException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelStation {
    private String id;
    private String name;
    private String address;
    private Location location;
    private Map<Fuel, Integer> capacities;
    private Map<Fuel, Integer> stocks;
    private Map<Fuel,Integer> evaporationRate;
    private List<ProductTemplate> products;
    private Instant lastSupplyDate;

    public void performSupply(int gasolineQuantity, int dieselQuantity, int petrolQuantity) {
        int gasolineCapacity = capacities.get(Fuel.gasoline);
        int petrolCapacity = capacities.get(Fuel.petrol);
        int dieselCapacity = capacities.get(Fuel.diesel);

        int gasolineStocked = stocks.get(Fuel.gasoline);
        int petrolStocked = stocks.get(Fuel.petrol);
        int dieselStocked = stocks.get(Fuel.diesel);

        Map<Fuel, Integer> fuelCapacities = new HashMap<>();
        fuelCapacities.put(Fuel.gasoline, gasolineCapacity);
        fuelCapacities.put(Fuel.diesel, dieselCapacity);
        fuelCapacities.put(Fuel.petrol, petrolCapacity);

        Map<Fuel, Integer> fuelStocks = new HashMap<>();
        fuelStocks.put(Fuel.gasoline, gasolineStocked);
        fuelStocks.put(Fuel.diesel, dieselStocked);
        fuelStocks.put(Fuel.petrol, petrolStocked);

        if (fuelCapacities.get(Fuel.gasoline) < gasolineQuantity) {
            throw new InsufficientCapacityException("Insufficient storage capacity for gasoline ");
        }

        if (fuelCapacities.get(Fuel.diesel) < dieselQuantity) {
            throw new InsufficientCapacityException("Insufficient storage capacity for diesel");

        }

        if (fuelCapacities.get(Fuel.petrol) < petrolQuantity) {
            throw new InsufficientCapacityException("Insufficient storage capacity for petrol");

        } else {
            fuelStocks.put(Fuel.gasoline, gasolineStocked + gasolineQuantity);
            fuelStocks.put(Fuel.diesel, dieselStocked + dieselQuantity);
            fuelStocks.put(Fuel.petrol, petrolStocked + petrolQuantity);

            stocks.put(Fuel.gasoline, fuelStocks.get(Fuel.gasoline));
            stocks.put(Fuel.diesel, fuelStocks.get(Fuel.diesel));
            stocks.put(Fuel.petrol, fuelStocks.get(Fuel.petrol));
            lastSupplyDate = Instant.now();
        }


    }

    public void sellFuelByLiters(ProductTemplate fuel, int quantity) {
        int maxQuantityPerSale = 200;
        int currentStock = stocks.get(fuel.getName());

        if (currentStock < quantity) {
            throw new InsufficientStockException("Insufficient fuel stock");
        }
        if (quantity > maxQuantityPerSale) {
            throw new IllegalArgumentException("Exceeded maximum quantity per sale");
        }
        stocks.put(fuel.getName(), currentStock - quantity);
    }

    public void sellFuel(ProductTemplate fuel, int amount) {
        int maxQuantityPerSale = 200;
        int currentStock = stocks.get(fuel.getName());
        int pricePerLiter = fuel.getUnitPrice();
        int quantity = amount / pricePerLiter;
        if (currentStock < quantity) {
            throw new InsufficientStockException("Insufficient fuel stock");
        }
        if (quantity > maxQuantityPerSale) {
            throw new IllegalArgumentException("Exceeded maximum quantity per sale");
        }
        stocks.put(fuel.getName(), currentStock - quantity);

    }
}

