package com.carapp.search;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    FileService fileService;


    @GetMapping("/getCar")
    public List<Car> getCarById(){
        List<Car> list = new ArrayList<>();
        carRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/{fold}/{filePath}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fold,
                                           @PathVariable String filePath) throws IOException {
        File img = new File("src/main/images/" + fold + "/" + filePath);
        System.out.println(fold);
        System.out.println(filePath);
        System.out.println(img.toPath());
        return ResponseEntity.ok()
                             .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
                             .body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("/filterCar")
    public List<Car> filter(@RequestParam(value = "maker", required = false) String maker,
                            @RequestParam(value = "model", required = false) String model,
                            @RequestParam(value = "min_year", required = false) Integer min_year,
                            @RequestParam(value = "max_year", required = false) Integer max_year,
                            @RequestParam(value = "min_price", required = false) Integer min_price,
                            @RequestParam(value = "max_price", required = false) Integer max_price, HttpServletRequest req) throws IOException {
        //System.out.println(req.getInputStream());
        System.out.println(model);
        System.out.println(maker);
        System.out.println(min_year);
        System.out.println(max_year);
        System.out.println(min_price);
        System.out.println(max_price);
        //System.out.println(carRepository.filterCar(maker, model, min_year, max_year, min_price, max_price));
        return carRepository.filterCar(maker.isEmpty() ? null :maker,model.isEmpty() ? null :model, min_year, max_year, min_price, max_price);
    }

    @PostMapping("/createCar")
    //@Secured("ROLE_ADMIN")
    public String createCar(@RequestParam(value = "maker", required = true) String maker,
                                    @RequestParam(value = "model", required = true) String model,
                                    @RequestParam(value = "year", required = true) Integer year,
                                    @RequestParam(value = "price", required = true) Integer price,
                                    @RequestPart(value = "image", required = true) MultipartFile image){
        System.out.println(maker);
        System.out.println(image);
        System.out.println(model);
        System.out.println(year);
        System.out.println(price);
        System.out.println(image.getOriginalFilename());
        fileService.uploadFile(image);
        Car car = new Car();
        car.setMaker(maker);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        car.setImage(image.getOriginalFilename());

        carRepository.save(car);
        return "Car created succesfully";
    }

    @PutMapping("/updateCar")
    //@Secured("ROLE_ADMIN")
    public Car updateCar(@RequestBody Car oldCar){
        System.out.println(oldCar);
        System.out.println(oldCar.getId());
            Car car = carRepository.findById(oldCar.getId()).get();
            if(car != null) {
                BeanUtils.copyProperties(oldCar, car, "image");
                carRepository.save(car);
            }
            return oldCar;
    }

    @DeleteMapping("/deleteCar")
    //@Secured("ROLE_ADMIN")
    public String deleteCar(@RequestParam(value = "id", required = true) Long id){
        System.out.println(id);
        System.out.println("I was called while deleting car in service");
        carRepository.deleteById(id);
        return "Car deleted succesfully";
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Long id){
        Optional<Car> optionalUser = carRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

}