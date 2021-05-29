package com.github.tharishs.tbr.controller;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.model.detail.ProductDetails;
import com.github.tharishs.tbr.service.ProductService;
import com.github.tharishs.tbr.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "api/product/v1", produces = MediaType.APPLICATION_JSON_VALUE)
class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/get/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves all product details for a takealot product url that includes a PLID")
    public ResponseEntity<ProductDetails> getProductDetails(@RequestParam String takealotUrl) throws ServiceException, IntegrationException {
        String plid = StringUtil.extractPlid(takealotUrl);
        return ResponseEntity.ok(productService.getProductDetails(plid));
    }

    @GetMapping(value = "/get/product/price", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves the price for a given takealot product url that includes a PLID")
    public ResponseEntity<Map<String, String>> getProductPrice(@RequestParam String takealotUrl) throws ServiceException, IntegrationException {
        String plid = StringUtil.extractPlid(takealotUrl);
        Map<String, String> response = new HashMap<>();
        ProductDetails productDetails = productService.getProductDetails(plid);
        response.put("price", productService.getPrice(productDetails) + "");
        response.put("plid", plid);
        return ResponseEntity.ok(response);
    }

}
