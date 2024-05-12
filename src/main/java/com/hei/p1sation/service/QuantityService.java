package com.hei.p1sation.service;

import com.hei.p1sation.model.enums.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuantityService {
    private QuantityService quantityService;

    public float getTotalSupply() {
        return quantityService.getTotalSupply();
    }

    public float getTotalSale() {
        return quantityService.getTotalSale();
    }

    public float getTotalSaleByFuel(Fuel name) {
        return quantityService.getTotalSaleByFuel(name);
    }

    public float getTotalBySupplyByFuel(Fuel name) {
        return quantityService.getTotalBySupplyByFuel(name);
    }

    public float getStocksByFuel(Fuel name) {
        return quantityService.getTotalBySupplyByFuel(name) - quantityService.getTotalSaleByFuel(name);
    }
}
