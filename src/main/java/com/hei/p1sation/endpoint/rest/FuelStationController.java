package com.hei.p1sation.endpoint.rest;

import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.service.FuelStationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/station")
@AllArgsConstructor
public class FuelStationController {
    private FuelStationService fuelStationService;

    @GetMapping
    public List<FuelStation> findAll() {
        return fuelStationService.findAll();
    }

    @PostMapping("/{fuelStationId}/sell/byAmount")
    public ResponseEntity<String> sellFuelByAmount(
            @PathVariable String fuelStationId,
            @RequestParam(name = "fuel") Fuel fuel,
            @RequestParam(name = "amount") int amount) {
        try {
            fuelStationService.sellFuelByAmount(fuelStationId, fuel, amount);
            return ResponseEntity.ok("Fuel sold successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sell fuel.");
        }
    }

    @PostMapping("/{fuelStationId}/sell/byLiters")
    public ResponseEntity<String> sellFuelByLiters(
            @PathVariable String fuelStationId,
            @RequestParam(name = "fuel") Fuel fuel,
            @RequestParam(name = "amount") int amount) {
        try {
            fuelStationService.sellFuelByLiters(fuelStationId, fuel, amount);
            return ResponseEntity.ok("Fuel sold successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sell fuel.");
        }
    }

    @GetMapping("/{fuelStationId}/storage")
    public float getStorageBetweenTwoDate(
            @PathVariable String fuelStationId,
            @RequestParam( name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant endDate
    ) {


        return fuelStationService.getStorageBetweenDate(fuelStationId, startDate, endDate);
    }
}
