package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.controllers;

import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.MaxSaleDayDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.SaleAmountDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.TopRoutesDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/today")
    public SaleAmountDTO getTodaySales() {
        return salesService.getTotalSalesToday();
    }

    @GetMapping("/max-sale-day")
    public MaxSaleDayDTO getMaxSaleDay(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return salesService.getMaxSaleDay(startDate, endDate);
    }

    @GetMapping("/top-routes")
    public List<TopRoutesDTO> getTopSellingRoutes() {
        return salesService.getTopSellingRoutes();
    }
}