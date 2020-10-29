package ru.cakes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cakes.entities.Customer;

@Controller
@RequestMapping("/{customerId}")
public class CakeController {

    @GetMapping("/")
    public String mainPage(@PathVariable Customer customer) {
        return "cakes";
    }
}
