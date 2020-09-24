package com.carapp.search;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface    CarRepository extends CrudRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE (:maker is null or c.maker = :maker) and (:model is null or c.model = :model)" +
            "and (:min_year is null or c.year >= :min_year) and  (:max_year is null or c.year <= :max_year)" +
            "and (:min_price is null or c.price >= :min_price) and  (:max_price is null or c.price <= :max_price)")
    List<Car> filterCar(@Param("maker") String maker, @Param("model") String model,
                        @Param("min_year") Integer min_year,
                        @Param("max_year") Integer max_year,
                        @Param("min_price") Integer min_price, @Param("max_price") Integer max_price);
}

/*public interface    CarRepository extends CrudRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE (:maker is null or c.maker = :maker) and (:model is null or c.model = :model)" +
            "and (:min_year is null or c.year >= :min_year) and  (:max_year is null or c.year <= :max_year)" +
            "and (:min_price is null or c.price >= :min_price) and  (:max_price is null or c.price <= :max_price)")
    List<Car> filterCar(@Param("maker") String maker, @Param("model") String model,
                        @Param("min_year") Integer min_year,
                        @Param("max_year") Integer max_year,
                        @Param("min_price") Integer min_price, @Param("max_price") Integer max_price);
}
*/