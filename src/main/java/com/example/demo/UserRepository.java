package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Grzegorz", "Ryczkowski", 23));
        users.add(new User("Dawid", "Jakistam", 17));
        users.add(new User("Agata", "Madra", 37));
    }

    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    public void add(User user) {
       users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }
}
