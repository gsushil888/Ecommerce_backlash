package com.backlashprogramming.ecommerce.EcommerceByBacklash.service;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.dao.OrderDao;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Orders> getOrders(LocalUser user){
        return  orderDao.findByUser(user);
    }

}
