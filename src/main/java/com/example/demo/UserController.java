package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    String users() {
        List<User> users = userRepository.getAll();

        String result = "";

        for (User user : users) {
            result += user.toString() + "</br>";
        }

        return result;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(required = false, defaultValue = "Anonim") String name, @RequestParam String lastName, @RequestParam int age) {

        if ("".equals(name)) {
            return "redirect:/err.html";
        } else {
            User user = new User(name, lastName, age);
            userRepository.add(user);
            return "redirect:/success.html";
        }
    }
    @ResponseBody
    @RequestMapping("/find")
    public String find(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "") String lastName, @RequestParam(required = false, defaultValue = "0") int age) {

            List<User> users = userRepository.getAll();

            String result = "";

            for (User user : users) {
                if(user.getName().equalsIgnoreCase(name) || user.getLastName().equalsIgnoreCase(lastName) || user.getAge() == age) {
                    result += user.toString() + "</br>";
                }
            }
            if("".equals(result)) {
                return "<h1>Nie znaleziono takiego użytkownika</h1>";
            }
            return result;
        }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "") String lastName, @RequestParam(required = false, defaultValue = "0") int age) {

        List<User> users = userRepository.getAll();

        for (User user : users) {
            if(user.getName().equalsIgnoreCase(name) || user.getLastName().equalsIgnoreCase(lastName) || user.getAge() == age) {
                userRepository.remove(user);
                return "<h1>Udało Ci się usunąć użytkownika!</h1>";
            }
        }
        return "<h1>Nie ma użytkownika o podanym parametrze!</h1>";
}
}
