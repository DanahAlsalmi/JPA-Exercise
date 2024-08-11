package com.example.capstoneupdate.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username must be more than 5 characters long")
    @Column(columnDefinition = "varchar(15) not null")
    @Check(constraints = "username > 5")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$", message = "Password must contain letter and number")

    @Column(columnDefinition = "varchar (15) not null")
    @Check(constraints = "password ~ '^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$'")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be contain '@' ")

    @Column(columnDefinition = "varchar(40) not null")
    @Check(constraints = "email LIKE '%@%'")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "Admin|Customer", message = "Role must be either Admin or Customer")

    @Column(columnDefinition = "varchar(8) not null ")
    @Check(constraints = "role IN ('Admin','Customer) ")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")

    @Column(columnDefinition = "decimal(10,2) not null")
    @Check(constraints = "LENGTH(balance) > 0")
    private double balance;

//    @AssertFalse
    @Column(columnDefinition = "boolean not null default false")
    @Check(constraints = "isPrime = false")
    private boolean is_prime;

    @Column(columnDefinition = "date")
    private LocalDate primeStartDate;
    @Column(columnDefinition = "date")
    private LocalDate primeEndDate;




//    private List<Product> wishlist = new ArrayList<>();
//
//
//    public List<Product> getWishlist() {
//        return wishlist;
//    }
//
//    public void setWishlist(List<Product> wishlist) {
//        this.wishlist = wishlist;
//    }

    //Wish List
//    private ArrayList<Product> wishlist;
//
//    public User() {
//        this.wishlist = new ArrayList<>(); // Initialize the wishlist
//    }



}
