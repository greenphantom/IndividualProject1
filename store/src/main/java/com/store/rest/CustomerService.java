package com.store.rest;
import com.store.dao.CustomerDAO;
import com.store.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    CustomerDAO customerDAO = new CustomerDAO();

    public Customer getCustomer(String username){
        return  customerDAO.getCustomer(username);
    }

    public Customer createCustomer(String fname,String lname,String username,String email){
        return customerDAO.createCustomer(fname,lname,username,email);
    }

    public void updateCustomer(Customer customer){
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(String username){
        customerDAO.deleteCustomer(username);
    }
}
