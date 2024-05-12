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
public class LocationDAO implements CrudRepository<Location> {
    private Connection connection;

    @Override
    public Location save(Location toSave) {
        String sql = """
                INSERT INTO "location" (latitude,longitude) VALUES(?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, toSave.getLatitude());
            preparedStatement.setString(2, toSave.getLongitude());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Location getById(String id) {
        String sql = """
                Select * from "location" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getString("id"));
                location.setLatitude(resultSet.getString("latitude"));
                location.setLongitude(resultSet.getString("longitude"));
                return location;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        String sql = """
                DELETE from "location" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                FuelStation fuelStationDeleted = new FuelStation(id);
                System.out.println("location deleted" + fuelStationDeleted);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Location> findAll() {
        List<Location> locationList = new ArrayList<>();
        String sql = """
                SELECT * FROM "location";
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                locationList.add(new Location(
                        resultSet.getString("id"),
                        resultSet.getString("latitude"),
                        resultSet.getString("longitude")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationList;
    }
}
