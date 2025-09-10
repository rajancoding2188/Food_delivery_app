package com.ecommerce.controller;


import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.UserDetails;
import com.ecommerce.service.User_Details_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class User_Details {

    private final User_Details_Service user_details_service;

    public User_Details(User_Details_Service user_details_service) {

        this.user_details_service = user_details_service;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDetails userDetails) {
        try {
            user_details_service.createUser(userDetails);
            return ResponseEntity.ok("User Order created successfully");
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Value is not allowed");
        }


    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDetails userDetails) {
        try {
            user_details_service.updateUser(userDetails);
            return ResponseEntity.ok("User Order Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runtime Error");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        try {
            List<OrderResponse> orders = user_details_service.getAllOrders(page,size);

            if (orders.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(orders); // 200 OK with list of orders
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderResponse>> getOrderById(@PathVariable Long id) {
        try {
            List<OrderResponse> orders = user_details_service.getOrderById(id);

            if (orders.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(orders); // 200 OK with list of orders
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
