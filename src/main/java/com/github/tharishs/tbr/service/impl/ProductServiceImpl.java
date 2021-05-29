package com.github.tharishs.tbr.service.impl;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.integration.TakealotApiClient;
import com.github.tharishs.tbr.model.detail.ProductDetails;
import com.github.tharishs.tbr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final TakealotApiClient takealotApiClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDetails getProductDetails(String plid) throws IntegrationException {
        return takealotApiClient.getProductDetails(plid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice(ProductDetails product) {
        return product.getDataLayer().getTotalPrice();
    }

}
