package com.carapp.apigateway.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/orders")
@FeignClient(value = "order")
public interface OrderClient {
    @GetMapping("/getOrder")
    //@Secured("ROLE_USER")
    public List<Order> getOrders(@RequestParam(value = "currId", required = true) Long id, @RequestParam(value = "currRole", required = true) String role );

//    @PostMapping("/reserve")
//    //@Secured("ROLE_USER")
//    public Order createOrder(@RequestParam(value = "id", required = true) Long id,
//                                              @RequestParam(value = "startDate", required = true) Date startDate,
//                                              @RequestParam(value = "endDate", required = true) Date endDate,
//                                              @RequestParam(value = "userid", required = true) Long userId) throws ParseException;

    @PostMapping("/reserve")
    //@Secured("ROLE_USER")
    public Order createOrder(@RequestBody Order order) throws ParseException;

    @DeleteMapping("/deleteOrder")
    //@Secured("ROLE_ADMIN")
    public String deleteOrder(@RequestParam(value = "id", required = true) Long id);
}
