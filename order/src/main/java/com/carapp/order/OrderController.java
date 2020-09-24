package com.carapp.order;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("")
public class OrderController{
    @Autowired
    OrderRepository orderRepository;


    @GetMapping("/api/orders/getOrder")
    //@Secured("ROLE_USER")
    public List<Order> getOrders(@RequestParam(value = "currId", required = true) Long id, @RequestParam(value = "currRole", required = true) String role ){

        Iterable<Order> orderList = new LinkedList<>();
        if (role.equals("ROLE_ADMIN")){
            orderList = orderRepository.findAll();
            System.out.println(orderList + "admin");
        }
        else{
            orderList = orderRepository.findByUserId(id);
            System.out.println(orderList + " user");
        }
        List<Order> list = new ArrayList<>();
        orderList.iterator().forEachRemaining(list::add);
        return list;
//        System.out.println(orderList + "hrtfer");
//        List<String> res = new LinkedList<>();;
//        for (Order order: orderList) {
//            Gson gson = new Gson();
//            String json = gson.toJson(order);
//            res.add(json);
//        }
//        System.out.println(res + "Wrong date comp!!!!!!!!!!!!!1");
//        return res;
    }

    @PostMapping("/api/orders/reserve")
    //@Secured("ROLE_USER")
    public Order createOrder(@RequestBody Order order1) throws ParseException {

        Order order  = new Order();

        Date now = new Date();

        Date date1=order1.getStartDate();
        Date date2=order1.getEndDate();

        System.out.println(order1.getStartDate()+" order1.getStartDate()");
        System.out.println(order1.getEndDate()+" order1.getEndDate()");
//        System.out.println(now+" now");
//        System.out.println(date1.before(now)+" date1.before(now)");
//        System.out.println(date1.after(date2)+" date1.after(date2)");


        if(date1.before(now)||date1.after(date2)) {
            System.out.println("if(date1.before(now)||date1.after(date2)");
            return null;
        }
        System.out.println("line75");
        Iterable<Order> orderList = orderRepository.findAll();
        for (Order temp : orderList) {
             Date start = temp.getStartDate();
            Date end = temp.getEndDate();
            System.out.println(temp.getStartDate() +" temp.getStartDate()");
            System.out.println(temp.getEndDate()+" temp.getEndDate()");
            System.out.println(temp.getCarId()+" temp.getCarId()");
            System.out.println(order1.getCarId()+" order1.getCarId()");
            System.out.println(date1.after(start)+" (date1.after(start)");
            System.out.println(date1.before(end)+" date1.before(end)");
            System.out.println((date1.after(start)||date1.before(end))+" (date1.after(start)||date1.before(end))");
            System.out.println(date2.after(start)+" date2.after(start)");
            System.out.println(date2.before(end)+" date2.before(end)");
            System.out.println((date2.after(start)&&date2.before(end)) +" (date2.after(start)&&date2.before(end)) ");

            if(temp.getCarId() == order1.getCarId() && (date1.after(start)&&date1.before(end)) || (date2.after(start)&&date2.before(end)) ) {
                System.out.println("Wrong date");
                return null;
            }
        }

        orderRepository.save(order1);
        return order1;
    }


    @DeleteMapping("/api/orders/deleteOrder")
    //@Secured("ROLE_ADMIN")
    public String deleteOrder(@RequestParam(value = "id", required = true) Long id){
        System.out.println(id);
        orderRepository.deleteById(id);
        return "Deleted";
    }
}
