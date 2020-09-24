package com.carapp.apigateway.search;

import lombok.*;

import javax.persistence.*;

@Data
public class Car {

    private Long id;

    private String maker;

    private String model;

    private int year;

    private int price;

    private String image;

}

