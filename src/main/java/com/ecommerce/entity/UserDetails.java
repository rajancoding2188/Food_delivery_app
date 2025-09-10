package com.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "OrderResponseMapping",
        classes = @ConstructorResult(
                targetClass = com.ecommerce.dto.OrderResponse.class,
                columns = {
                        @ColumnResult(name = "customer_name", type = String.class),
                        @ColumnResult(name = "total_amount", type = Double.class),
                        @ColumnResult(name = "item_name", type = String.class),
                        @ColumnResult(name = "orderDateTime", type = java.time.LocalDateTime.class)
                }
        )
)
@Table(
        name = "userdetails",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"customer_name"}
        )
)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    private String customer_name;

    @OneToMany(mappedBy = "userDetails",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItems> items;

    private Integer total_amount;

    private LocalDateTime orderDateTime;


}
