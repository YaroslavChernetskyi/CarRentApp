package com.carapp.apigateway.feedback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/api/feedbacks")
@FeignClient(value = "feedback")
public interface FeedbackClient {
    @GetMapping("/getComments//{id}")
    public List<Feedback> getOrders(@PathVariable Long id);

    @PostMapping("/comment")
    //@Secured("ROLE_USER")
    public Feedback createOrder(@RequestParam(value = "carId", required = true) Long carId,
                                @RequestParam(value = "stars", required = true) Integer stars,
                                @RequestParam(value = "comment", required = true) String comment,
                                @RequestParam(value = "userId", required = true) Long userId
    ) throws ParseException ;
}
