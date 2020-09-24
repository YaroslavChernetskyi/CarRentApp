package com.carapp.apigateway.feedback;



import com.carapp.apigateway.auth.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FeedbackController {
    @Autowired
    FeedbackClient feedbackClient;

    @Autowired
    AuthClient authClient;

    @GetMapping("/getComments//{id}")
    public List<Feedback> getOrders(@PathVariable Long id){
        return feedbackClient.getOrders(id);
    }


    @PostMapping("/comment")
    //@Secured("ROLE_USER")
    public Feedback createOrder(@RequestParam(value = "carId", required = true) Long carId,
                                @RequestParam(value = "stars", required = true) Integer stars,
                                @RequestParam(value = "comment", required = true) String comment,
                                @RequestHeader(value = "Authorization") String token
                               ) throws ParseException {

        Long userId = authClient.getCurrentUserId(token);

        return feedbackClient.createOrder(carId, stars, comment, userId);
    }

}
