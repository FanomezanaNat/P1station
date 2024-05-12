package com.hei.p1sation.repository.dao;

import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.Location;
import com.hei.p1sation.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class FuelStationDAO implements CrudRepository<FuelStation> {
    private Connection connection;
    private LocationDAO locationDAO;

    @Override
    public FuelStation save(FuelStation toSave) {
        String sql = """ 
                INSERT INTO "fuel_station" (address,id_location) VALUES (?,?);
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, toSave.getAddress());
            statement.setString(2, toSave.getLocation().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public FuelStation getById(String id) {
        String sql = """
                SELECT * FROM "fuel_station" where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id_location = resultSet.getString("id_location");
                Location location = locationDAO.getById(id_location);
                FuelStation fuelStation = new FuelStation();
                fuelStation.setId(resultSet.getString("id"));
                fuelStation.setAddress(resultSet.getString("address"));
                fuelStation.setLocation(location);
                return fuelStation;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void deleteById(String id) {
        String sql = """
                DELETE from "fuel_station" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                FuelStation fuelStationDeleted = new FuelStation(id);
                System.out.println("fuelStation deleted" + fuelStationDeleted);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<FuelStation> findAll() {
        List<FuelStation> fuelStations = new ArrayList<>();
        String sql = """
                Select * from fuel_station;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id_location");
                Location location = locationDAO.getById(id);
                fuelStations.add(new FuelStation(
                        resultSet.getString("id"),
                        resultSet.getString("address"),
                        location
                ));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return fuelStations;
    }
}
