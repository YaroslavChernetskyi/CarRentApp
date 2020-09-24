package com.carapp.apigateway.search;


import com.carapp.apigateway.order.OrderClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    CarClient carClient;

    @Autowired
    OrderClient orderClient;


    @GetMapping("/getCar")
    public List<Car> getCarById(){
        return carClient.getCarById();
    }

    @GetMapping("/cars/{filePath:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filePath) throws IOException {
        System.out.println(filePath + "filepath");
        return carClient.getImage("cars", filePath);
    }

    @GetMapping("/filterCar")
    public List<Car> filter(@RequestParam(value = "maker", required = false) String maker,
                            @RequestParam(value = "model", required = false) String model,
                            @RequestParam(value = "min_year", required = false) Integer min_year,
                            @RequestParam(value = "max_year", required = false) Integer max_year,
                            @RequestParam(value = "min_price", required = false) Integer min_price,
                            @RequestParam(value = "max_price", required = false) Integer max_price) throws IOException {
        return carClient.filter(maker, model,min_year, max_year, min_price, max_price);
    }

    @PostMapping("/createCar")
    //@Secured("ROLE_ADMIN")
    public String createCar(@RequestParam(value = "maker", required = true) String maker,
                                    @RequestParam(value = "model", required = true) String model,
                                    @RequestParam(value = "year", required = true) Integer year,
                                    @RequestParam(value = "price", required = true) Integer price,
                                    @RequestPart(value = "image", required = true) MultipartFile image){
        return carClient.createCar(maker, model, year, price, image);
    }

    @PutMapping("/updateCar")
    //@Secured("ROLE_ADMIN")
    public Car updateCar(@RequestBody Car oldCar){
        return carClient.updateCar(oldCar);
    }

    @DeleteMapping("/deleteCar")
    //@Secured("ROLE_ADMIN")
    public String deleteCar(@RequestParam(value = "id", required = true) Long id){

        System.out.println("I was called while deleting car in gateway");
        return carClient.deleteCar(id);
    }

    @GetMapping("/car/{id}")
    public Car getOne(@PathVariable Long id){
        return carClient.getOne(id);
    }

}