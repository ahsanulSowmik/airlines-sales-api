package com.apurbatechnologiesltdtask.airlines.airlines_sales_api.models.dtos;

import lombok.Data;

import java.time.LocalDate;


@Data
public class MaxSaleDayDTO {
    private LocalDate date;
    private double totalSales;

}
