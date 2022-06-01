package br.com.login.modules.user.services;

import br.com.login.modules.user.entities.User;
import br.com.login.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User execute(User user) {

        User existsUser = userRepository.findByEmail(user.getEmail());

        if (existsUser != null) {
            throw new Error("User already exists!");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        return createdUser;
    }
}
