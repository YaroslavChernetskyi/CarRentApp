package com.carapp.order;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private Long carId;

    @Column
    @NonNull
    private Long userId;

    @Column
    @NonNull
    private Date startDate;
    @Column
    @NonNull
    private Date endDate;
}