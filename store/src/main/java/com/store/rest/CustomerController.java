package com.store.rest;


import com.store.model.Customer;
import org.json.JSONObject;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        }
        catch (Exception e){
            System.out.println("Error?");
        }
    }

    // Lookup user
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInJSON(@PathParam("username") String username) {
        Customer c = customerService.getCustomer(username);
        JSONObject jsonObject = new JSONObject(c);
        if (c != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Create User
    @POST
    @Path("")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@QueryParam("fname") String fname,@QueryParam("lname") String lname, @QueryParam("username") String username,@QueryParam("email") String email){
        Customer c = customerService.createCustomer(fname,lname,username,email);
        return Response.status(200).build();
    }

    // Update User
    @PUT
    @Path("")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@QueryParam("fname") String fname,@QueryParam("lname") String lname, @QueryParam("username") String username,@QueryParam("email") String email){
        Customer c = customerService.getCustomer(username);
        if (c == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            c.setFname(fname);
            c.setLname(lname);
            c.setEmail(email);
            c.setUsername(username);
            customerService.updateCustomer(c);
            return Response.status(200).build();
        }
    }

    // Delete User
    @DELETE
    @Path("/{username}")
    public Response removeUser(@PathParam("username") String username){
        customerService.deleteCustomer(username);
        return Response.status(200).build();
    }
}

