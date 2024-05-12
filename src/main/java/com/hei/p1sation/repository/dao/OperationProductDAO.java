package com.hei.p1sation.repository.dao;


import com.hei.p1sation.model.OperationProduct;
import com.hei.p1sation.model.Product;
import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.model.enums.TransactionType;
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
public class OperationProductDAO implements CrudRepository<OperationProduct> {
    private Connection connection;
    private ProductDAO productDAO;


    @Override
    public OperationProduct save(OperationProduct toSave) {

        String sql = """
                INSERT INTO "operation_product" (type, product_id,quantity) VALUES(?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toSave.getType());
            preparedStatement.setObject(2, toSave.getProduct());
            preparedStatement.setFloat(3, toSave.getQuantity());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public OperationProduct getById(String id) {
        String sql = """
                Select * from "operation_product" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id_product = resultSet.getString("product_id");
                Product product = productDAO.getById(id_product);
                OperationProduct operation = new OperationProduct();
                operation.setId(resultSet.getString("id"));
                operation.setDate(resultSet.getTimestamp("date").toInstant());
                operation.setType((TransactionType) resultSet.getObject("type"));
                operation.setProduct(product);
                operation.setQuantity(resultSet.getFloat("quantity"));
                return operation;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        String sql = """
                DELETE from "operation_product" where id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                OperationProduct operationProductDeleted = new OperationProduct(id);
                System.out.println("operation product deleted" + operationProductDeleted);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<OperationProduct> findAll() {
        List<OperationProduct> operationProductList = new ArrayList<>();
        String sql = """
                SELECT * FROM "operation_product";
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id_product = resultSet.getString("product_id");
                Product product = productDAO.getById(id_product);
                operationProductList.add(new OperationProduct(
                                resultSet.getString("id"),
                                resultSet.getTimestamp("date").toInstant(),
                                (TransactionType) resultSet.getObject("type"),
                                product,
                                resultSet.getFloat("quantity")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return operationProductList;
    }
    public int getCountByFuel(Fuel fuelType) {
        String sql = "SELECT COUNT(*) FROM operation_product op WHERE EXISTS (SELECT 1 FROM product p JOIN product_template pt ON p.product_template_id = pt.id WHERE op.product_id = p.id AND pt.name = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fuelType.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + e.getMessage(), e);
        }
    }

}
