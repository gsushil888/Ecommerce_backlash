package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.controller;


import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Orders;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.OrderService;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public List<Orders> getOrders(@AuthenticationPrincipal LocalUser user){
        return orderService.getOrders(user);
    }


}
