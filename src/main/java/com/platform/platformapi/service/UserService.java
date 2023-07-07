package com.platform.platformapi.service;

import com.platform.platformapi.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
        User user1 = new User(1, "Ida", 32, "ida@mail.com");
        User user2 = new User(2, "Tom", 18, "tom@mail.com");
        User user3 = new User(3, "Paula", 55, "paula@mail.com");
        User user4 = new User(4, "Lisa", 48, "lisa@mail.com");
        User user5 = new User(5, "Thomas", 33, "thomas@mail.com");
        User user6 = new User(6, "Theodora", 25, "theodora@mail.com");
        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5,user6));
    }

    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user : userList) {
            if (id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

}
