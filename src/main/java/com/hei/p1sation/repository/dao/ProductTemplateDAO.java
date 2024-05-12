package com.hei.p1sation.repository.dao;


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

public class ProductTemplateDAO implements CrudRepository<ProductTemplate> {
    private Connection connection;

    @Override
    public ProductTemplate save(ProductTemplate toSave) {
        String sql = """
                INSERT INTO "product_template" (name,unit_price) VALUES(?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toSave.getName());
            preparedStatement.setInt(2, toSave.getUnitPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ProductTemplate getById(String id) {
        String sql = """
                SELECT * FROM "product_template" where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate();
                productTemplate.setId(resultSet.getString("id"));
                productTemplate.setName((Fuel) resultSet.getObject("name"));
                productTemplate.setUnitPrice(resultSet.getInt("unit_price"));
                return productTemplate;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        String sql = """
                DELETE from "product_template" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                ProductTemplate productTemplateDeleted = new ProductTemplate(id);
                System.out.println("productTemplate deleted" + productTemplateDeleted);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ProductTemplate> findAll() {
        List<ProductTemplate> productTemplateList = new ArrayList<>();
        String sql = """
                SELECT * FROM "product_template";
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                productTemplateList.add(new ProductTemplate(
                        resultSet.getString("id"),
                        (Fuel) resultSet.getObject("name"),
                        resultSet.getInt("unit_price")

                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productTemplateList;
    }
    public ProductTemplate getByName(Fuel name){
        String sql = """
                SELECT * FROM "product_template" where name = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate();
                productTemplate.setId(resultSet.getString("id"));
                productTemplate.setName(Fuel.valueOf(resultSet.getString("name")));
                productTemplate.setUnitPrice(resultSet.getInt("unit_price"));
                return productTemplate;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
