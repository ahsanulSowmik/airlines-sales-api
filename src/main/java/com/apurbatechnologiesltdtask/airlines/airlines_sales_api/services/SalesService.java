package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.services;

import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.MaxSaleDayDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.SaleAmountDTO;
import com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos.TopRoutesDTO;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {
    SaleAmountDTO getTotalSalesToday();
    MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate);
    List<TopRoutesDTO> getTopSellingRoutes();
}
