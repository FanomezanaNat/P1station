package com.hei.p1sation.service;

import com.hei.p1sation.model.OperationProduct;
import com.hei.p1sation.repository.dao.OperationProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OperationProductService {
    private OperationProductDAO operationProductDAO;

    public List<OperationProduct> findAll() {
        return operationProductDAO.findAll();
    }

    public OperationProduct save(OperationProduct toSave) {
        return operationProductDAO.save(toSave);
    }

    public void deleteById(String id) {
        operationProductDAO.deleteById(id);
    }

    public OperationProduct getById(String id) {
        return operationProductDAO.getById(id);
    }




}
