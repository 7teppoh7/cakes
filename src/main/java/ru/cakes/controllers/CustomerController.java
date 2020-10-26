package ru.cakes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cakes.entities.Customer;
import ru.cakes.services.CustomerService;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Boolean error) {
        model.addAttribute("customers", customerService.findAll());
        if (error != null && error) model.addAttribute("error", "Данные введены неверно");
        return "hello";
    }

    @PostMapping("/")
    public String createCustomer(Customer customer) {
        if (!customerService.isCustomerValid(customer)) return "redirect:/?error=true";
        customerService.create(customer);
        return "redirect:/";
    }

}
