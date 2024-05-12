package com.hei.p1sation.service;

import com.hei.p1sation.model.Product;
import com.hei.p1sation.repository.dao.ProductDAO;
import com.hei.p1sation.repository.dao.ProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductDAO productDAO;

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product save(Product product) {
        return productDAO.save(product);
    }

    public void deleteById(String id) {
        productDAO.deleteById(id);
    }

    public Product getById(String id) {
        return productDAO.getById(id);
    }
}
