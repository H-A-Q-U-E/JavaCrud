package com.jac.jd.Application.java.Controller;
import com.jac.jd.Application.java.model.User;
import com.jac.jd.Application.java.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserRepository userRepository;

        public UserController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        // CREATE
        @PostMapping
        public User createUser(@RequestBody User user) {
            return userRepository.save(user);
        }

        // READ ALL
        @GetMapping
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        // READ ONE
        @GetMapping("/{id}")
        public User getUser(@PathVariable Long id) {
            return userRepository.findById(id).orElse(null);
        }

        // UPDATE
        @PutMapping("/{id}")
        public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
            return userRepository.findById(id)
                    .map(user -> {
                        user.setName(updatedUser.getName());
                        user.setEmail(updatedUser.getEmail());
                        return userRepository.save(user);
                    })
                    .orElse(null);
        }

        // DELETE
        @DeleteMapping("/{id}")
        public String deleteUser(@PathVariable Long id) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        }
    }


