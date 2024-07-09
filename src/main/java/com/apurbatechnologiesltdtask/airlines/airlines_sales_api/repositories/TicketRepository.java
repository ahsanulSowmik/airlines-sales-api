package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories;


import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
