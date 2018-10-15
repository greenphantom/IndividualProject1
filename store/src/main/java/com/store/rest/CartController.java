package com.store.rest;

import com.store.model.Customer;
import com.store.model.Product;
import org.json.JSONObject;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/carts")
public class CartController extends HttpServlet {
    private CartService cartService = new CartService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        }
        catch (Exception e){
            System.out.println("Error?");
        }
    }

    // Create Cart / Adds Item to Cart
    @POST
    @Path("")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@QueryParam("productId") int productId, @QueryParam("username") String username){
        cartService.addItemtoCart(productId,username);
        return Response.status(200).build();
    }

    // Lookup cart
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInJSON(@QueryParam("username") String username) {
        ArrayList<Object> c = cartService.getCart(username);
        JSONObject jsonObject = new JSONObject(c);
        if (c != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Remove Item from Cart
    @DELETE
    @Path("/")
    public Response removeUser(@QueryParam("cartId") int cartId, @QueryParam("productId") int productId){
        cartService.removeItemFromCart(cartId,productId);
        return Response.status(200).build();
    }

    // Buy Item
    @PUT
    @Path("/purchase/{cartId}")
    public Response buyItem(@PathParam("cartId") int cartId){
        cartService.buyItem(cartId);
        return Response.status(200).build();
    }

    // List users who bought a product
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsersBoughtProduct(@QueryParam("productId") int productId) {
        ArrayList<Customer> c = cartService.listBuyersofItem(productId);
        JSONObject jsonObject = new JSONObject(c);
        if (c != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // List products bought by user
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProductsBoughtByUser(@QueryParam("username") String username) {
        ArrayList<Product> c = cartService.listProductsBoughtByUser(username);
        JSONObject jsonObject = new JSONObject(c);
        if (c != null) {
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
