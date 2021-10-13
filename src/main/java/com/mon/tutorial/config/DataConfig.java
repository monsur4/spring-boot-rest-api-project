package com.mon.tutorial.config;

import com.mon.tutorial.entity.User;
import com.mon.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DataConfig {
    UserRepository userRepository;

    @Autowired
    public DataConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // initialize the database
    /*@Bean
    public CommandLineRunner run(){
        return args -> {
            userRepository.save(new User("aliu", "kano", "email", new Date(), "temi", new Date(), "titi" ));
            userRepository.save(new User("aliu", "kano", "email", new Date(), "temi", new Date(), "titi" ));
            userRepository.save(new User("aliu", "kano", "email", new Date(), "temi", new Date(), "titi" ));
        };
    }*/
}
