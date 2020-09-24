package com.carapp.apigateway.order;

import com.carapp.apigateway.auth.AuthClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController{
    @Autowired
    OrderClient orderClient;

    @Autowired
    AuthClient authClient;

    @GetMapping("/getOrder")
    //@Secured("ROLE_USER")
    public List<String> getOrders(@RequestHeader(value = "Authorization") String token){
        List<Object> curUser = authClient.getCurrentUser(token);
        Long id = authClient.getCurrentUserId(token);
        String role = curUser.get(0).toString();
        List<String> res = new LinkedList<>();;
        for (Order order: orderClient.getOrders(id, role)) {
            Gson gson = new Gson();
            String json = gson.toJson(order);
            res.add(json);
        }
        return res;


    }
//    @PostMapping("/reserve")
//    //@Secured("ROLE_USER")
//    public Order createOrder(@RequestParam(value = "id", required = true) Long id,
//                                              @RequestParam(value = "startDate", required = true) String startDate,
//                                              @RequestParam(value = "endDate", required = true) String endDate,
//                                              @RequestHeader(value = "Authorization") String token) throws ParseException {
//        List<Object> curUser = authClient.getCurrentUser(token);
//        System.out.println(" idrole ");
//        System.out.println(authClient.getCurrentUserId(token));
//        System.out.println(curUser);
//        System.out.println(" idrole ");
//        Long userId = authClient.getCurrentUserId(token);
//        System.out.println(id +" idrole in gateway create");
//        System.out.println(startDate + " received in gateway ");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=dateFormat.parse(startDate);
//        Date date2=dateFormat.parse(endDate);
//        System.out.println(date1 + " parsed in gateway ");
//        return orderClient.createOrder(id,date1, date2, userId);
//
//
//
//
//    }

    @DeleteMapping("/deleteOrder")
    //@Secured("ROLE_ADMIN")
    public String deleteOrder(@RequestParam(value = "id", required = true) Long id){
        return orderClient.deleteOrder(id);
    }
}
