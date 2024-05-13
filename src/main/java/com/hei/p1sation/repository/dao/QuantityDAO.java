package com.hei.p1sation.repository.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
@AllArgsConstructor
public class QuantityDAO {
    private Connection connection;

    public float getTotalSupplyByFuelBetweenDate(String id, Instant startDate,Instant endDate) {
        String sql = """
                SELECT get_total_supply_by_id_product_between_date(?,?,?);
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.setTimestamp(2, Timestamp.from(startDate));
            preparedStatement.setTimestamp(3, Timestamp.from(endDate));

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
    public float getTotalSaleByFuelBetweenDate(String id,Instant startDate,Instant endDate) {
        String sql = """
                SELECT get_total_sale_by_id_product_between_date(?,?,?);
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.setTimestamp(2, Timestamp.from(startDate));
            preparedStatement.setTimestamp(3, Timestamp.from(endDate));

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

}
