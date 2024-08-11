package com.example.capstoneupdate.Service;


import com.example.capstoneupdate.Model.User;
import com.example.capstoneupdate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Get users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Add User
    public void addUser(User user) {
        userRepository.save(user);
    }

    //Update user
    public boolean updateUser(Integer id,User user) {
        User u = userRepository.getById(id);
        if (u != null) {
            u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.set_prime(user.is_prime());
            u.setBalance(user.getBalance());
            u.setRole(user.getRole());
            userRepository.save(u);
            return true;

        }
        return false;
    }

    //Delete user
    public boolean deleteUser(Integer id) {
        User u = userRepository.getById(id);
        if (u != null) {
            userRepository.delete(u);
            return true;
        }
        return false;
    }

    //Get user by ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    //Prime subscribe
    public User subscribeToPrime(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null; // Return null if the user is not found
        }

        if (user.getBalance() < 100) {
            return null; // Return null if the user does not have enough balance
        }

        user.setBalance(user.getBalance() - 100);
        user.set_prime(true);  // Assuming the correct field is 'isPrime'
        user.setPrimeStartDate(LocalDate.now());
        user.setPrimeEndDate(LocalDate.now().plusYears(1));
        userRepository.save(user);

        return user;
    }

    public boolean canclePrime(Integer id) {
        User u = userRepository.getById(id);
        if (u != null && u.is_prime()) {
            u.set_prime(false);
            userRepository.save(u);
            return true;
        }
        return false;
    }

}
