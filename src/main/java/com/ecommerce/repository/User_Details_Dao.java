package com.ecommerce.repository;

import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.OrderItems;
import com.ecommerce.entity.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class User_Details_Dao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(UserDetails userDetails, List<OrderItems> items) {

        for (OrderItems item : items) {
            item.setUserDetails(userDetails); // link user to each item
        }
        userDetails.setItems(items);
        entityManager.persist(userDetails);
    }

    public void update(UserDetails userDetails) {

        userDetails.setOrderDateTime(LocalDateTime.now());
        entityManager.merge(userDetails);
    }

    public List<OrderResponse> allOrders(int page, int size) {

        return entityManager.createNativeQuery(
                        "select c.customer_name,c.total_amount,o.item_name,c.orderDateTime from userdetails as c inner join orderitems as o on c.customer_id=o.customer_id",
                        "OrderResponseMapping")
                .setFirstResult(page*size).setMaxResults(size)
                .getResultList();
    }

    public List<OrderResponse> getOrderById(Long id) {

        return entityManager.createNativeQuery(
                        "select c.customer_name,c.total_amount,o.item_name,c.orderDateTime from userdetails as c inner join orderitems as o on c.customer_id=o.customer_id where c.customer_id=" + id + "",
                        "OrderResponseMapping")
                .getResultList();
    }



}
