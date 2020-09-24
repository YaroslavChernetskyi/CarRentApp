package com.carapp.feedback;



import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/getComments/{id}")
    public List<Feedback> getOrders(@PathVariable Long id){
        List<Feedback> list = new ArrayList<>();
        feedbackRepository.findByCarId(id).iterator().forEachRemaining(list::add);
        return list;
    }


    @PostMapping("/comment")
    //@Secured("ROLE_USER")
    public Feedback createOrder(@RequestParam(value = "carId", required = true) Long carId,
                                @RequestParam(value = "stars", required = true) Integer stars,
                                @RequestParam(value = "comment", required = true) String comment,
                                @RequestParam(value = "userId", required = true) Long userId
                               ) throws ParseException {

        Feedback feedback = new Feedback();

        feedback.setCarId(carId);
        feedback.setUserId(userId);

        feedback.setComment(comment);
        feedback.setStars(stars);

        feedbackRepository.save(feedback);
        return feedback;
    }

}
