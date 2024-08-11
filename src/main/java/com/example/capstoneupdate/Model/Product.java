package com.example.capstoneupdate.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;


@Data
@AllArgsConstructor

@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "LENGTH(productName) > 3)")
    private String productName;

    @NotNull(message = "Price must be not Null")
    @Positive(message = "Price must be positive")
    @Column(columnDefinition = " int not null")
    @Check(constraints = "LENGTH(productPrice) > 0")
    private double productPrice;

    @NotNull(message = "Category ID must not be empty")
    @Column(columnDefinition = "int not null")
    private int productCategoryId;



}