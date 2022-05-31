package br.com.login.controllers;

import br.com.login.model.User;
import br.com.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {

        Optional<User> userFind = this.userRepository.findById(id);

        if(userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/")
    public void createUser( @RequestBody User user) {
        this.userRepository.save(user);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping("/delete")
    public void deleteUser( @RequestBody User user ) { this.userRepository.delete(user);}
}
