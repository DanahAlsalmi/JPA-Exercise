package com.example.capstoneupdate.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@Entity
@NoArgsConstructor
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID must not be empty")
    @Column(columnDefinition = "int not null")
    private int productId;

    @NotNull(message = "Merchant ID must not be empty")
    @Column(columnDefinition = "int not null")
    private int merchantId;

    @NotNull(message = "Stock must be not null")
    @Min(value = 10, message = "Stock must be more than 10 at start")
    @Column(columnDefinition = "int not null")
    private int stock;



}