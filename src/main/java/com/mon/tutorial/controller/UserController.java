package com.mon.tutorial.controller;

import com.mon.tutorial.entity.User;
import com.mon.tutorial.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return (List<User>)userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //put
    @PutMapping(path="/{id}", consumes = "application/json")
    public User replaceUser(@RequestBody User user, @PathVariable("id") Long id){
        // this new user's id is set to the id of the corresponding entry in the database and the entity is
        // saved into the database, essentially replacing the former entry
        user.setId(id);
        return userRepository.save(user);
    }

    // update
    @PatchMapping(path="/update/{id}", consumes = "application/json")
    public User updateUser(@RequestBody User patch, @PathVariable("id") Long id){
        User user = userRepository.findById(id).get();
        if(patch.getFirstName() != null){
            user.setFirstName(patch.getFirstName());
        };
        if(patch.getLastName() != null){
            user.setLastName(patch.getLastName());
        };
        if(patch.getEmail() != null){
            user.setEmail(patch.getEmail());
        };
        if(patch.getCreatedAt() != null){
            user.setCreatedAt(patch.getCreatedAt());
        };
        if(patch.getCreatedBy() != null){
            user.setCreatedBy(patch.getCreatedBy());
        }
        if(patch.getUpdatedAt() != null){
            user.setUpdatedAt(patch.getUpdatedAt());
        }
        if(patch.getUpdatedBy() != null){
            user.setUpdatedBy(patch.getUpdatedBy());
        }
        return userRepository.save(user);
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        try{
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            log.info("entry did not exist in the database");
        }
    }
}
