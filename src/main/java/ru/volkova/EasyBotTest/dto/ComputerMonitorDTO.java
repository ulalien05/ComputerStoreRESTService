package ru.volkova.EasyBotTest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ComputerMonitorDTO {

    @NotEmpty(message = "Serial number must be entered.")
    private String serialNumber;

    private String manufacturer;

    @Min(value = 0, message = "Price cannot be negative.")
    private double price;

    @Min(value = 0, message = "Stock quantity cannot be negative.")
    private int stockQuantity;

    @NotNull(message = "Diagonal must be entered.")
    private Double diagonal;
}
