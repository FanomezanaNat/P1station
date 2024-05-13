package com.hei.p1sation.service;

import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.OperationProduct;
import com.hei.p1sation.model.Product;
import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.repository.dao.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static com.hei.p1sation.model.enums.TransactionType.sale;
import static com.hei.p1sation.model.enums.TransactionType.supply;

@Service
@AllArgsConstructor
public class FuelStationService {
    private FuelStationDAO fuelStationDAO;
    private OperationProductDAO operationProductDAO;
    private ProductDAO productDAO;
    private QuantityDAO quantityDAO;

    public List<FuelStation> findAll() {
        return fuelStationDAO.findAll();
    }

    public FuelStation save(FuelStation fuelStation) {
        return fuelStationDAO.save(fuelStation);
    }

    public void deleteById(String id) {
        fuelStationDAO.deleteById(id);
    }

    public FuelStation getById(String id) {
        return fuelStationDAO.getById(id);
    }

    public void performSupply(String fuelStationId, Fuel productTemplateName, float quantity) {
        Product product = productDAO.findByFuelNameAndIdStation(productTemplateName, fuelStationId);
        FuelStation station = fuelStationDAO.getById(fuelStationId);
        OperationProduct operationProduct = new OperationProduct(
                supply,
                product,
                quantity
        );

        operationProductDAO.save(operationProduct);


    }

    public void sellFuelByLiters(String fuelStationId, Fuel productTemplateName, float quantity) {
        var product = productDAO.findByFuelNameAndIdStation(productTemplateName, fuelStationId);
        OperationProduct operationProduct = new OperationProduct(
                sale,
                product,
                quantity
        );
        operationProductDAO.save(operationProduct);

    }

    public void sellFuelByAmount(String fuelStationId, Fuel productTemplateName, int amount) {
        Product product = productDAO.findByFuelNameAndIdStation(productTemplateName, fuelStationId);
        var unitPrice = product.getProduct().getUnitPrice();
        OperationProduct operationProduct = new OperationProduct(
                sale,
                product,
                (amount / unitPrice)
        );
        operationProductDAO.save(operationProduct);

    }

    public float getStorageBetweenDate(String id, Instant startDate, Instant endDate) {
        Product product = productDAO.getById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product with ID: " + id + " not found.");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }

        float evaporationRate = product.getEvaporationRate();
        long daysBetween = Duration.between(startDate, endDate).toDays();
        float potentialEvaporationLoss = evaporationRate * daysBetween;
        float storage = quantityDAO.getTotalSupplyByFuelBetweenDate(id, startDate, endDate) - quantityDAO.getTotalSaleByFuelBetweenDate(id, startDate, endDate);
        return storage - potentialEvaporationLoss;

    }
}
