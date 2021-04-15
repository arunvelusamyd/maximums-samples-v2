package com.flexy.kafka.jsonstore;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public String productName;

    public String productRating;

    public String productDescription;

    public Product(String productName, String productRating, String productDescription) {
        this.productName = productName;
        this.productRating = productRating;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
