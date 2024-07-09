package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories;


import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findBySaleDate(LocalDate saleDate);
    List<Ticket> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);

}
