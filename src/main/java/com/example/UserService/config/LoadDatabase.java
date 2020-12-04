package com.example.UserService.config;

import com.example.UserService.constants.UserServiceConstants;
import com.example.UserService.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDB(UserRepository userRepository) {
        return args -> {
            IntStream.rangeClosed(1, 8).forEach(i -> {
                log.info(UserServiceConstants.PRELOADING + userRepository.save(getUser()));
            });
        };
    }

    private User getUser() {
        Random random = new Random();
        String userID = String.valueOf(random.nextInt(9999));
        Address address = new Address(userID, UserServiceConstants.STREET, UserServiceConstants.CITY,
                UserServiceConstants.STATE, UserServiceConstants.POST_CODE);
        User user = new User(userID, Title.Master, UserServiceConstants.FIRST_NAME.concat(userID), UserServiceConstants.LAST_NAME, Gender.Male, UserServiceConstants.EMPLOYEE_ID.concat(userID));
        user.setAddress(address);
        return user;
    }

}
