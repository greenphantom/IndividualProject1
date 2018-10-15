package com.store.rest;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.ShoppingCarts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    private ShoppingCarts carts = new ShoppingCarts();

    public void addItemtoCart(int itemID,String username){
        carts.addItemtoCart(itemID,username);
    }

    public ArrayList<Object> getCart(String username){
        return carts.getCart(username);
    }

    public void removeItemFromCart(int cartID,int productID){
        carts.removeItemFromCart(cartID,productID);
    }

    public void buyItem(int cartId){
        carts.buyItem(cartId);
    }

    public ArrayList<Customer> listBuyersofItem(int productID) {
        return carts.listBuyersofItem(productID);
    }

    public ArrayList<Product> listProductsBoughtByUser(String username){
        return carts.listItemsBoughtbyCustomer(username);
    }
}
