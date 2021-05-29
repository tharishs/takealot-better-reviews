package com.github.tharishs.tbr.service;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.model.detail.ProductDetails;

public interface ProductService {


    ProductDetails getProductDetails(String plid) throws IntegrationException;

    int getPrice(ProductDetails product);
}
