package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories;

import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}