package com.carapp.order;

//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.socket.TextMessage;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000")
//@Controller
////@RequestMapping("/api/orders")
//public class OrderSocket {
//
//
//        @Autowired
//        OrderRepository orderRepository;
//
//        @MessageMapping("/changeMessage")
//        @SendTo("/topic/activity")
//        public String message(@RequestBody Order order1, @RequestHeader(value = "Authorization") String token ) throws ParseException {
//            System.out.println(order1 + " Im here");
//            System.out.println(token["Authorization"] + " Im here token");
//            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//
//            return "hello";
//
////            Order order  = new Order();
////
////            //LocalDate aLD = LocalDate.parse(order1.getStartDate().toString());
////            //System.out.println(aLD + "Wrong date comp");
////            //Date aLD1 = aLD;
////            //System.out.println(aLD1 + "Wrong date comp");
////
////            System.out.println(order1.getStartDate().toString() + "Wrong date comp");
////            DateFormat df = new SimpleDateFormat("EEE MMM dd  yyyy");
////
////            Date now = new Date();
////            //Date date1=dateFormat.parse(order1.getStartDate().toString());
////            //Date date2=dateFormat.parse(order1.getEndDate().toString());
////
////            Date date1=order1.getStartDate();
////            Date date2=order1.getEndDate();
////
////            if(date1.before(now)||date1.after(date2)) {
////                System.out.println("Wrong date comp");
////                return null;
////            }
////
////            Iterable<Order> orderList = orderRepository.findAll();
////            for (Order temp : orderList) {
////                Date start = temp.getStartDate();
////                Date end = temp.getEndDate();
////                System.out.println(temp.getEndDate() + "  test1");
////                System.out.println(temp.getEndDate() + "  test1");
////
////
////                if(temp.getCarId() == order1.getCarId() && (date1.after(now)&&date1.before(end)) || (date2.after(now)&&date2.before(end)) ) {
////                    System.out.println("Wrong date");
////                    return null;
////                }
////            }
////
////            order.setCarId(order1.getCarId());
////            order.setUserId(order1.getUserId());
////
////            order.setStartDate(date1);
////            order.setEndDate(date2);
////
////            System.out.println(date1);
////            System.out.println(date2);
////
////            orderRepository.save(order);
////            List<String> res = new LinkedList<>();
////            List<String> res2 = new LinkedList<>();
////            Gson gson = new Gson();
////            for (Order or: orderList) {
////
////                String json = gson.toJson(or);
////                System.out.println(or.getStartDate() + "startdate");
////                System.out.println(or.getEndDate() + "endtdate");
////                res.add(json.toString());
////            }
////            String json = gson.toJson(order);
////            res.add(json.toString());
////
////            return res;
//        }
//
//    }
