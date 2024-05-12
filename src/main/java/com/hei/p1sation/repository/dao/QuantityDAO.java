package com.hei.p1sation.repository.dao;

import com.hei.p1sation.model.enums.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class QuantityDAO {
    private Connection connection;

    public float getTotalSupply(){
        String sql= """
                SELECT get_total_supply() AS total_supply
                """;
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat("total_supply");
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching total supply", e);
        }
    }
    public float getTotalSale(){
        String sql= """
                SELECT get_total_sale() AS total_sale
                """;
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat("total_sale");
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching total sale", e);
        }
    }
    public float getTotalSupplyByFuel(Fuel name) {
        String sql = """
                SELECT get_total_supply(?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage(), e);
        }
    }
    public float getTotalSaleByFuel(Fuel name) {
        String sql = """
                SELECT get_total_sale(?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage(), e);
        }
    }
    public float currentStockFuelWithoutEvaporationRate(Fuel name){
        return getTotalSupplyByFuel(name) - getTotalSaleByFuel(name);
    }
}
