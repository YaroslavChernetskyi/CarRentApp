package com.carapp.apigateway.order;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
public class Order {

    private Long id;

    private Long carId;

    private Long userId;

    private Date startDate;

    private Date endDate;
}