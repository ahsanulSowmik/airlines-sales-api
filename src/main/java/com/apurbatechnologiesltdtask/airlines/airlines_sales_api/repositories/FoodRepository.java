package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories;

import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findBySaleDate(LocalDate saleDate);

}