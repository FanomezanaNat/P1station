package com.hei.p1sation.repository.dao;


import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.ProductTemplate;
import com.hei.p1sation.model.Storage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
@AllArgsConstructor
public class StorageDAO {
    private Connection connection;
    private FuelStationDAO fuelStationDAO;
    private ProductTemplateDAO productTemplateDAO;

    public Storage save(Storage toSaved) {
        String sql = """
                INSERT INTO "storage"(station_id, product_template_id, value) VALUES (?,?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toSaved.getFuelStation().getId());
            preparedStatement.setObject(2, toSaved.getProduct());
            preparedStatement.setFloat(3, toSaved.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Storage getStorage(Instant datetime, String idStation, String idProduct) throws SQLException {
//
        String sql = """
                SELECT * FROM "storage"
                WHERE last_updated <= ? AND station_id = ? AND product_template_id =?
                ORDER BY last_updated DESC  LIMIT 1;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setTimestamp(1, Timestamp.from(datetime));
                preparedStatement.setString(2, idStation);
                preparedStatement.setString(3, idProduct);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String id_fuel_station = resultSet.getString("station_id");
                        String id_product_template = resultSet.getString("product_template_id");
                        FuelStation fuelStation = fuelStationDAO.getById(id_fuel_station);
                        ProductTemplate productTemplate = productTemplateDAO.getById(id_product_template);
                        Storage storage = new Storage();
                        storage.setId(resultSet.getString("id"));
                        storage.setFuelStation(fuelStation);
                        storage.setProduct(productTemplate.getName());
                        storage.setValue(resultSet.getFloat("value"));
                        storage.setLast_updated(resultSet.getTimestamp("last_updated").toInstant());

                        return storage;
                    }
                }
            }
            return null;
        }

    }

