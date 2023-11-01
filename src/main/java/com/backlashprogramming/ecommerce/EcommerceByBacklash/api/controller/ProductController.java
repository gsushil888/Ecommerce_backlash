package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.controller;


import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Orders;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Product;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.OrderService;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    

}
