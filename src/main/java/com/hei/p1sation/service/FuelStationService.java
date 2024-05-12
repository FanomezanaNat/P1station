package com.hei.p1sation.service;

import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.OperationProduct;
import com.hei.p1sation.model.Product;
import com.hei.p1sation.model.Storage;
import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.repository.dao.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hei.p1sation.model.enums.TransactionType.sale;
import static com.hei.p1sation.model.enums.TransactionType.supply;

@Service
@AllArgsConstructor
public class FuelStationService {
    private FuelStationDAO fuelStationDAO;
    private OperationProductDAO operationProductDAO;
    private ProductDAO productDAO;
    private StorageDAO storageDAO;

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
        Storage storage = new Storage(
                quantity,
                station,
                productTemplateName


        );
        operationProductDAO.save(operationProduct);
        storageDAO.save(storage);


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
}
