package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.services.impl;

import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.MaxSaleDayDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.SaleAmountDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.TopRoutesDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Food;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.entities.Ticket;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories.FoodRepository;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.repositories.TicketRepository;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SalesServiceImpl implements SalesService {
    private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public SaleAmountDTO getTotalSalesToday() {
        LocalDate today = LocalDate.now();
        double ticketSales = ticketRepository.findBySaleDate(today).stream().mapToDouble(Ticket::getPrice).sum();
        double foodSales = foodRepository.findBySaleDate(today).stream().mapToDouble(Food::getPrice).sum();

        SaleAmountDTO dto = new SaleAmountDTO();
        dto.setTotalSales(ticketSales + foodSales);

        logger.info("Total sales for today calculated: {}", dto.getTotalSales());
        return dto;
    }

    @Override
    public MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        List<Ticket> tickets = ticketRepository.findBySaleDateBetween(startDate, endDate);
        LocalDate maxSaleDate = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getSaleDate, Collectors.summingDouble(Ticket::getPrice)))
                .entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
                .get().getKey();

        double totalSales = ticketRepository.findBySaleDate(maxSaleDate).stream().mapToDouble(Ticket::getPrice).sum();

        MaxSaleDayDTO dto = new MaxSaleDayDTO();
        dto.setDate(maxSaleDate);
        dto.setTotalSales(totalSales);

        logger.info("Max sale day between {} and {} is {} with total sales of {}", startDate, endDate, maxSaleDate, totalSales);
        return dto;
    }

    @Override
    public List<TopRoutesDTO> getTopSellingRoutes() {
        return ticketRepository.findAll().stream()
                .collect(Collectors.groupingBy(Ticket::getRoute, Collectors.summingDouble(Ticket::getPrice)))
                .entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3).map(entry -> {
                    TopRoutesDTO dto = new TopRoutesDTO();
                    dto.setRoute(entry.getKey());
                    dto.setTotalSales(entry.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }
}