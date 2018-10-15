package com.store.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.store.model.Customer;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDAO {
    private JdbcTemplate jdbcTemplate;
    // Based off in class example
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomerDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Collection<Customer> getCustomers(){
        Collection<Customer> customers = new ArrayList<Customer>();
        this.jdbcTemplate.query(
                "select * from customers",new Object[] {},
                (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("fname"),rs.getString("lname"),rs.getString("username"),rs.getString("email"))
        ).forEach(customer -> customers.add(customer));
        return customers;
    }

    public void printCustomers(){
        Collection<Customer> base = getCustomers();
        System.out.println("[");
        for (Customer c:base) {
            System.out.println("\t" + c + ",");
        }
        System.out.println("]");
    }

    // CRUD functions
    public Customer createCustomer(Customer customer){
        String sql_cmd = "insert into customers (id,fname,lname,username,email) "
                + "values(" + customer.getId() +",'" + customer.getFname() +"','"+ customer.getLname() +"','"+ customer.getUsername() +"','"+ customer.getEmail()+"');";
        System.out.println(sql_cmd);
        this.jdbcTemplate.execute(sql_cmd);
        return customer;
    }

    public Customer createCustomer(String fname,String lname,String username,String email){
        String sql_cmd = "insert into customers (fname,lname,username,email) "
                + "values('"+ fname +"','"+ lname +"','"+ username+"','"+ email+"');";
        System.out.println(sql_cmd);
        this.jdbcTemplate.execute(sql_cmd);
        return new Customer(fname,lname,username,email);
    }



    public Customer getCustomer(String username){
        String sql_cmd = "SELECT * FROM customers WHERE username = '"
                + username + "';";
        System.out.println(sql_cmd);
        Customer customer = new Customer();
        this.jdbcTemplate.query(sql_cmd, (rs) -> {
                //customer.setId(rs.getInt("id"));
                customer.setFname(rs.getString("fname"));
                customer.setLname(rs.getString("lname"));
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
            });
        return customer;
    }

    public Customer updateCustomer(Customer customer){
        String sql_cmd = "UPDATE customers "
                +"SET fname = '" + customer.getFname() + "', lname = '" + customer.getLname() + "', username = '" + customer.getUsername() + "', email = '" + customer.getEmail()+"'"
                +" WHERE id = " + customer.getId() +";";
        System.out.println(sql_cmd);
        this.jdbcTemplate.update(sql_cmd);
        return customer;
    }

    public boolean deleteCustomer(Customer customer){
        boolean success = false;
        String sql_cmd = "DELETE FROM customers"
                +" WHERE id = " + customer.getId() +";";
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

    public boolean deleteCustomer(String username){
        boolean success = false;
        String sql_cmd = "DELETE FROM customers"
                +" WHERE username = '" + username +"';";
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

}