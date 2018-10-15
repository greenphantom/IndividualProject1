package com.store.rest;


import com.store.model.Product;
import org.json.JSONObject;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/items")
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        }
        catch (Exception e){
            System.out.println("Error?");
        }
    }

    // List items by keyword
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listByKeyword() {
        ArrayList<Product> pList = (ArrayList<Product>) productService.listProducts();
        JSONObject jsonObject = new JSONObject(pList);
        if (pList != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // List all items
    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllItems(@PathParam("keyword") String keyword) {
        ArrayList<Product> pList = (ArrayList<Product>) productService.listByKeyword(keyword);
        JSONObject jsonObject = new JSONObject(pList);
        if (pList != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // List all items by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listItemsById(@PathParam("id") int id) {
        ArrayList<Product> pList = (ArrayList<Product>) productService.listById(id);
        JSONObject jsonObject = new JSONObject(pList);
        if (pList != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}