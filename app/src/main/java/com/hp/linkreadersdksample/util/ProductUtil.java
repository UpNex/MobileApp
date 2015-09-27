package com.hp.linkreadersdksample.util;

import com.hp.linkreadersdksample.Product;

import java.util.HashMap;

/**
 * Created by ramnivasindani on 9/26/15.
 */
public class ProductUtil {
    private static HashMap<String,Product> productMap = new HashMap<String,Product>();

    public static Product getProduct(String productId){
        return productMap.get(productId);
    }

    public static void setProduct(Product product){
        productMap.put(product.getProductId(), product);
    }
}
