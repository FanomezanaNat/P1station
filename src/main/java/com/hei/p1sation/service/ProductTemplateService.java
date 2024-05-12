package com.hei.p1sation.service;

import com.hei.p1sation.model.ProductTemplate;
import com.hei.p1sation.model.enums.Fuel;
import com.hei.p1sation.repository.dao.ProductTemplateDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductTemplateService {
    private ProductTemplateDAO productTemplateDAO;

    public List<ProductTemplate> findAll() {
        return productTemplateDAO.findAll();
    }

    public ProductTemplate save(ProductTemplate productTemplate) {
        return productTemplateDAO.save(productTemplate);
    }

    public void deleteById(String id) {
        productTemplateDAO.deleteById(id);
    }

    public ProductTemplate getById(String id) {
        return productTemplateDAO.getById(id);
    }
    public ProductTemplate getByName(Fuel name){
        return productTemplateDAO.getByName(name);

    }
}
