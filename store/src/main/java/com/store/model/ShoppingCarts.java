package com.store.model;
import com.store.dao.CustomerDAO;
import com.store.dao.ProductDAO;

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCarts {
    private int cartCount = 0;
    HashMap<Customer,ArrayList<Product>> shoppingCarts = new HashMap<>();
    HashMap<Integer,Customer> cartMap = new HashMap<>(); // Maps cart id to customer
    HashMap<Product,Customer> purchaseList = new HashMap<>();
    ProductDAO productDAO = new ProductDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    public ArrayList<Object> getCart(String username){
        Customer customer = customerDAO.getCustomer(username);
        ArrayList<Object> cart = new ArrayList<>();
        for (int cartID:cartMap.keySet()){
            if (cartMap.get(cartID) == customer){
                cart.add(cart.get(cartID));
            }
        }
        cart.add(shoppingCarts.get(customerDAO.getCustomer(username)));
        return cart;
    }

    public void addItemtoCart(int productId, String username){
        Customer customer = customerDAO.getCustomer(username);
        if (shoppingCarts.containsKey(customer)){
            ArrayList<Product> cart = shoppingCarts.get(customer);
            cart.add(productDAO.getProduct(productId));
        }
        else {
            ArrayList<Product> cart = shoppingCarts.get(customer);
            cart.add(productDAO.getProduct(productId));
            shoppingCarts.put(customer,cart);
            cartMap.put(cartCount++,customer);
        }
    }

    public void removeItemFromCart(int cartId,int itemId){
        Customer customer = cartMap.get(cartId);
        ArrayList<Product> contents = shoppingCarts.get(customer);
        contents.remove(productDAO.getProduct(itemId));
        shoppingCarts.put(customer,contents);
    }

    public void buyItem(int cartId){
        Customer customer = cartMap.get(cartId);
        ArrayList<Product> contents = shoppingCarts.get(customer);
        for (Product product: contents){
            purchaseList.put(product,customer);
        }
        shoppingCarts.remove(customer);
        cartMap.remove(cartId);
    }

    public ArrayList<Customer> listBuyersofItem(int itemId){
        return (ArrayList<Customer>) purchaseList.values();
    }

    public ArrayList<Product> listItemsBoughtbyCustomer(String username){
        Customer customer = customerDAO.getCustomer(username);
        ArrayList<Product> purchases = new ArrayList<>();
        for (Product product: purchaseList.keySet()){
            if (purchaseList.get(product) == customer){
                purchases.add(product);
            }
        }
        return  purchases;
    }


}
