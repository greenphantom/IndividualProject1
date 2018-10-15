package com.store.dao;
import com.store.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.Product;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDAO {
    private JdbcTemplate jdbcTemplate;
    // Based off in class example
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Collection<Product> getProducts(){
        Collection<Product> products = new ArrayList<Product>();
        this.jdbcTemplate.query(
                "select * from products",new Object[] {},
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"),rs.getFloat("msrp"),rs.getFloat("saleprice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(product -> products.add(product));
        return products;
    }

    public void printProducts(){
        Collection<Product> base = getProducts();
        System.out.println("[");
        for (Product c:base) {
            System.out.println("\t" + c + ",");
        }
        System.out.println("]");
    }

    public Collection<Product> getByKey(String key){
        Collection<Product> products = new ArrayList<Product>();
        this.jdbcTemplate.query(
                "select * from products WHERE MATCH (shortDescription) AGAINST '"+key+"';",new Object[] {},
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"),rs.getFloat("msrp"),rs.getFloat("saleprice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(product -> products.add(product));
        return products;
    }

    // CRUD functions
    public Product createProduct(Product product){
        String sql_cmd = "insert into products (itemId,name,msrp,salePrice,upc,shortDescription,brandName,size,color,gender) "
                + "values(" + product.getItemID() +",'" + product.getName() +"',"+ product.getMSRP() +","+ product.getSalePrice() +","+ product.getUPC() +",'"+ product.getShortDesc() +"','"+ product.getBrandName() +"','"+ product.getSize() +"','"+ product.getColor() +"','"+ product.getGender()+"');";
        System.out.println(sql_cmd);
        this.jdbcTemplate.execute(sql_cmd);
        return product;
    }

    public Product getProduct(int itemID){
        String sql_cmd = "SELECT * FROM products WHERE itemId = "
                + itemID + ";";
        System.out.println(sql_cmd);
        Product product = new Product();
        this.jdbcTemplate.query(
                sql_cmd,
                (rs) -> {
                    product.setItemID(rs.getInt("itemId"));
                    product.setName(rs.getString("name"));
                    product.setMSRP(rs.getFloat("msrp"));
                    product.setSalePrice(rs.getFloat("saleprice"));
                    product.setUPC(rs.getInt("upc"));
                    product.setShortDesc(rs.getString("shortDescription"));
                    product.setBrandName(rs.getString("brandName"));
                    product.setSize(rs.getString("size"));
                    product.setColor(rs.getString("color"));
                    product.setGender(rs.getString("gender"));
                });
        return product;
    }

    public Product updateProduct(Product product){
        String sql_cmd = "UPDATE products "
                +"SET name = '" + product.getName() + "', msrp = " + product.getMSRP() + ", salePrice = " + product.getSalePrice() + ", upc = " + product.getUPC() + ", shortDescription = '" + product.getShortDesc() + "', brandName = '" + product.getBrandName() + "', size = '" + product.getSize() + "', color = '" + product.getColor() + "', gender = '" + product.getGender()+"'"
                +" WHERE itemId = " + product.getItemID() +";";
        System.out.println(sql_cmd);
        this.jdbcTemplate.update(sql_cmd);
        return product;
    }

    public boolean deleteProduct(Product product){
        boolean success = false;
        String sql_cmd = "DELETE FROM products"
                +" WHERE itemId = " + product.getItemID() +";";
        System.out.println(sql_cmd);
        try {
            this.jdbcTemplate.update(sql_cmd);
            success = true;
        }
        catch (Exception e){
            System.out.println("Nothing to delete");
        }
        return success;
    }

    private DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    public Collection<Product> getById(int id) {
        Collection<Product> products = new ArrayList<Product>();
        this.jdbcTemplate.query(
                "select * from products WHERE itemId = "+id+";",new Object[] {},
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"),rs.getFloat("msrp"),rs.getFloat("saleprice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(product -> products.add(product));
        return products;
    }
}