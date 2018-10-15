package com.store.app;

import com.store.dao.CustomerDAO;
import com.store.dao.ProductDAO;
import com.store.model.Customer;
import com.store.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*import com.store.dao.*;
import com.store.model.*;
*/
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
While you are developing your DAO layer, you can configure maven to build a jar file
and use this class to perform tests, before moving on to implementing the REST layer.
*/

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
        try {
            Client client = Client.create();
            WebResource resource = client
                    .resource("http://localhost:8080/store-2.0.3.RELEASE/store/customers/prbrown");
            ClientResponse response = resource.accept("application/json")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("<<<< Generating JSON Output >>>");
            System.out.println(output);

            WebResource resource1 = client.resource("http://localhost:8080/store-2.0.3.RELEASE/store/customers?fname=Jane&lname=Doe&username=jdoe&email=jdoe@gmail.com");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        /** TODO:
         You use the provided .sql scripts to create and populate tables.
         Then, you can add calls to your CRUD operations from within this method.
         **/
        CustomerDAO customerDAO = new CustomerDAO(jdbcTemplate);
        ProductDAO productDAO = new ProductDAO(jdbcTemplate);

        log.info("Creating Customers");
        Customer joe = new Customer(7686, "Joe", "Rogen", "puppyLover261", "jre453@example.com");
        Customer jay = new Customer(8745, "Jay", "Leno", "kittyLover271", "jleno5@example.com");
        customerDAO.deleteCustomer(joe);  
        customerDAO.deleteCustomer(jay);
        customerDAO.createCustomer(joe);
        customerDAO.createCustomer(jay);
        customerDAO.printCustomers();


        Customer test = customerDAO.getCustomer("puppyLover261");
        test.setEmail("puppyFan@ufl.edu");
        customerDAO.updateCustomer(test);
        customerDAO.printCustomers();

        log.info("Creating Products");
        Product kicks = new Product(78541,"Fly Kicks",96,120,11111110,"Some fly kicks","Fila","11","Green and Silver","Unisex");
        Product scarf = new Product(78542,"Winter Scarf",16,20,11111111,"A pretty scarf","Unbranded","M","Gold","F");
        productDAO.deleteProduct(kicks);
        productDAO.deleteProduct(scarf);
        productDAO.createProduct(kicks);
        productDAO.createProduct(scarf);
        productDAO.printProducts();

        Product example = productDAO.getProduct(78541);
        example.setName("Super Fly Kickz");
        productDAO.updateProduct(example);
        productDAO.printProducts();//*/


    }
}
