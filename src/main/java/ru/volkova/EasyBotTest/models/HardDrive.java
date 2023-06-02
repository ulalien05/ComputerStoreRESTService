package ru.volkova.EasyBotTest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "hard_drive")
public class HardDrive {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "serial_number")
    @NotEmpty(message = "Serial number must be entered.")
    private String serialNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    @Min(value = 0, message = "Price cannot be negative.")
    private double price;

    @Column(name="stock_quantity")
    @Min(value = 0, message = "Stock quantity cannot be negative.")
    private int stockQuantity;

    @Column(name = "capacity")
    @NotNull(message = "Capacity must be entered.")
    private Integer capacity;
}
