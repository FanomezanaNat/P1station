package com.hei.p1sation.repository.dao;

import com.hei.p1sation.model.FuelStation;
import com.hei.p1sation.model.Product;
import com.hei.p1sation.model.ProductTemplate;
import com.hei.p1sation.model.enums.Fuel;
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
public class ProductDAO implements CrudRepository<Product> {
    private FuelStationDAO fuelStationDAO;
    private ProductTemplateDAO productTemplateDAO;
    private Connection connection;


    @Override
    public Product save(Product toSave) {
        String sql = """
                INSERT INTO "product"(evaporation_rate,fuel_station_id, product_template_id) VALUES (?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setFloat(1, toSave.getEvaporationRate());
            preparedStatement.setObject(2, toSave.getFuelStation());
            preparedStatement.setObject(3, toSave.getProduct());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product getById(String id) {
        String sql = """
                SELECT * FROM product WHERE id= ? ;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id_fuel_station = resultSet.getString("fuel_station_id");
                String id_product_template = resultSet.getString("product_template_id");
                FuelStation fuelStation = fuelStationDAO.getById(id_fuel_station);
                ProductTemplate productTemplate = productTemplateDAO.getById(id_product_template);
                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setEvaporationRate(resultSet.getFloat("evaporation_rate"));
                product.setFuelStation(fuelStation);
                product.setProduct(productTemplate);
                return product;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        String sql = """
                DELETE from "product" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                FuelStation fuelStationDeleted = new FuelStation(id);
                System.out.println("product deleted" + fuelStationDeleted);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sql = """
                SELECT * FROM "product";
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id_fuel_station = resultSet.getString("fuel_station_id");
                String id_product_template = resultSet.getString("product_template_id");
                FuelStation fuelStation = fuelStationDAO.getById(id_fuel_station);
                ProductTemplate productTemplate = productTemplateDAO.getById(id_product_template);
                productList.add(new Product(
                                resultSet.getString("id"),
                                resultSet.getFloat("evaporation_rate"),
                                fuelStation,
                                productTemplate
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public Product findByFuelNameAndIdStation(Fuel name, String idStation) {
        String sql = """
                SELECT product.*,"pt".name FROM product 
                INNER JOIN "product_template" pt on pt.id = product.product_template_id
                WHERE name=? AND pt.id=?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id_fuel_station = resultSet.getString("fuel_station_id");
                var id_product_template = resultSet.getString("product_template_id");
                var fuelStation = fuelStationDAO.getById(id_fuel_station);
                var productTemplate = productTemplateDAO.getById(id_product_template);
                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setEvaporationRate(resultSet.getFloat("evaporation_rate"));
                product.setFuelStation(fuelStation);
                product.setProduct(productTemplate);
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
