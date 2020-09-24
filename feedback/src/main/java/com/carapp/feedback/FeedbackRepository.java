package com.carapp.feedback;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    Optional<Feedback> findById(Long id);

    List<Feedback> findByUserId(Long userId);

    List<Feedback> findByCarId(Long carId);
}
