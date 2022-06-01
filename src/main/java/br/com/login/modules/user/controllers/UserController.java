package br.com.login.modules.user.controllers;

import br.com.login.modules.user.entities.User;
import br.com.login.modules.user.repository.UserRepository;
import br.com.login.modules.user.services.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CreateUserService createUserService;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {

        Optional<User> userFind = this.userRepository.findById(id);

        if(userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/create")
    public User createUser( @RequestBody User user) {
        return createUserService.execute(user);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userRepository.deleteById(id);
    }
}
