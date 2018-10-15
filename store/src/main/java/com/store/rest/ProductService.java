package com.store.rest;
import com.store.dao.ProductDAO;
import com.store.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService{
    ProductDAO productDAO = new ProductDAO();

    public Collection<Product> listProducts(){
        return productDAO.getProducts();
    }

    public Collection<Product> listByKeyword(String key){
        return productDAO.getByKey(key);
    }

    public Collection<Product> listById(int id){
        return productDAO.getById(id);
    }
}