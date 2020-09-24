package com.carapp.apigateway.search;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
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

@RequestMapping("/api/cars")
@FeignClient(value = "search")
public interface CarClient {
    @GetMapping("/getCar")
    public List<Car> getCarById();

    @GetMapping("/{fold}/{filePath}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fold,
                                           @PathVariable String filePath) throws IOException;

    @GetMapping("/filterCar")
    public List<Car> filter(@RequestParam(value = "maker", required = false) String maker,
                            @RequestParam(value = "model", required = false) String model,
                            @RequestParam(value = "min_year", required = false) Integer min_year,
                            @RequestParam(value = "max_year", required = false) Integer max_year,
                            @RequestParam(value = "min_price", required = false) Integer min_price,
                            @RequestParam(value = "max_price", required = false) Integer max_price) throws IOException;

    @PostMapping(value = "/createCar", consumes = "multipart/form-data")
    //@Secured("ROLE_ADMIN")
    public String createCar(@RequestParam(value = "maker", required = true) String maker,
                                    @RequestParam(value = "model", required = true) String model,
                                    @RequestParam(value = "year", required = true) Integer year,
                                    @RequestParam(value = "price", required = true) Integer price,
                                    @RequestPart(value = "image", required = true) MultipartFile image);

    @PutMapping("/updateCar")
    //@Secured("ROLE_ADMIN")
    public Car updateCar(@RequestBody Car oldCar);

    @DeleteMapping("/deleteCar")
    //@Secured("ROLE_ADMIN")
    public String deleteCar(@RequestParam(value = "id", required = true) Long id);

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Long id);
}
