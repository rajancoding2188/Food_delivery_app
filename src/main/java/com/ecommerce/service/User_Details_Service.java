package com.ecommerce.service;


import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.UserDetails;
import com.ecommerce.repository.User_Details_Dao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class User_Details_Service {

    private final User_Details_Dao userDetailsDao;

    public User_Details_Service(User_Details_Dao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    @Transactional
    public void createUser(UserDetails userDetails) {
        UserDetails user=new UserDetails();

        user.setCustomer_name(userDetails.getCustomer_name());
        user.setTotal_amount(userDetails.getTotal_amount());
        user.setOrderDateTime(LocalDateTime.now());

        userDetailsDao.save(user,userDetails.getItems());
    }
    public void updateUser(UserDetails userDetails) {
        userDetailsDao.update(userDetails);
    }

    public List<OrderResponse> getAllOrders(int page, int size) {
        return userDetailsDao.allOrders(page,size);
    }

    public List<OrderResponse> getOrderById(Long id) {
        return userDetailsDao.getOrderById(id);
    }


}
