package com.example.UserService.rest;

import com.example.UserService.dao.User;
import com.example.UserService.dao.UserRepository;
import com.example.UserService.exception.UserNotFoundException;
import com.example.UserService.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user/")
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * Constructor Initialization.
     *
     * @param userService
     */
    public UserController(final UserService userService) {

        this.userService = userService;
    }

    /**
     * Get all users endpoint.
     * @return
     */
    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        log.info("Fetch all users - Started");
        List<User> allUsers = userService.getAllUsers();
        log.info("Fetch all users - Completed");
        return allUsers;
    }

    /**
     * Get specific user
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getUser(@PathVariable final String id) {
        log.info("Fetch user details for [{}] - Started ", id);
        User user = userService.getUser(id);
        log.info("Fetch user details for [{}] - Finished ", id);
        return user;
    }

    /**
     * add user endpoint.
     * @param user
     * @return
     */
    @PostMapping("addUser")
    @ApiOperation(value = "add user method")
    public User createUser(@Valid @RequestBody final User user) {
        log.info("AddUser Request came for User [{}]", user.getFirstName());
        User saveUser = userService.addUser(user);
        log.info("User [{}] has been added Successfully to db ", user.getFirstName());
        return saveUser;

    }
}
