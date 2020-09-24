package com.carapp.apigateway.socket;

import com.carapp.apigateway.auth.AuthClient;
import com.carapp.apigateway.order.Order;
import com.carapp.apigateway.order.OrderClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
//@RequestMapping("/api/orders")
public class OrderSocket {

    @Autowired
    AuthClient authClient;

    @Autowired
    OrderClient orderClient;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/changeMessage/deleteOrder")
    public void deleteOrder(@RequestBody WebSocketMessage wbsm){
        List<Object> curUser = authClient.getCurrentUser(wbsm.getToken());
        Long id = authClient.getCurrentUserId(wbsm.getToken());
        String role = curUser.get(0).toString();

        orderClient.deleteOrder(wbsm.getOrder().getId());

        List<String> res = new LinkedList<>();
        ;
        for (Order item : orderClient.getOrders(id, role)) {
            Gson gson = new Gson();
            String json = gson.toJson(item);
            res.add(json);
        }

        List<String> adminRes = new LinkedList<>();
        ;
        for (Order item : orderClient.getOrders(id, "ROLE_ADMIN")) {
            Gson gson = new Gson();
            String json = gson.toJson(item);
            adminRes.add(json);
        }
        System.out.println(res + "Wrong date comp!!!!!!!!!!!!!1");

        this.template.convertAndSend("/topic/activity." + id, res);
        this.template.convertAndSend("/topic/activity.admin", adminRes);
    }

        @MessageMapping("/changeMessage")
        //@SendTo("/topic/activity")
        public void message(@RequestBody WebSocketMessage wbsm ) throws ParseException {
            List<Object> curUser = authClient.getCurrentUser(wbsm.getToken());
            Long id = authClient.getCurrentUserId(wbsm.getToken());
            String role = curUser.get(0).toString();

            Order order  = new Order();

            Order order1 = wbsm.getOrder();


            Date date1=order1.getStartDate();
            Date date2=order1.getEndDate();




            order.setCarId(order1.getCarId());
            order.setUserId(id);

            order.setStartDate(date1);
            order.setEndDate(date2);

            System.out.println(date1);
            System.out.println(date2);

            //Order t = orderClient.createOrder(order);
            if(orderClient.createOrder(order)==null) {
                this.template.convertAndSend("/topic/activity.error", "Empty");
            }
            else {


                List<String> res = new LinkedList<>();
                ;
                for (Order item : orderClient.getOrders(id, role)) {
                    Gson gson = new Gson();
                    String json = gson.toJson(item);
                    res.add(json);
                }

                List<String> adminRes = new LinkedList<>();
                ;
                for (Order item : orderClient.getOrders(id, "ROLE_ADMIN")) {
                    Gson gson = new Gson();
                    String json = gson.toJson(item);
                    adminRes.add(json);
                }
                System.out.println(res + "Wrong date comp!!!!!!!!!!!!!1");

                this.template.convertAndSend("/topic/activity." + id, res);
                this.template.convertAndSend("/topic/activity.admin", adminRes);
            }
            //return res;

        }

    }
