package com.example.capstoneupdate.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be more than 3 characters")
    @Column(columnDefinition = "varchar(15) not null unique ")
    private String categoryName;
}
