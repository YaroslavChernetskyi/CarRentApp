package com.carapp.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);

    List<Order> findByCarId(Long carId);
}
