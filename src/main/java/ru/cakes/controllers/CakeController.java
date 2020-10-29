package ru.cakes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cakes.entities.Customer;
import ru.cakes.services.CakeService;
import ru.cakes.services.CharacteristicService;
import ru.cakes.services.CustomerService;
import ru.cakes.services.DecorationService;

@Controller
@RequestMapping("/{customerId}")
public class CakeController {

    private final CustomerService customerService;

    private final CakeService cakeService;

    private final CharacteristicService characteristicService;

    private final DecorationService decorationService;

    public CakeController(CustomerService customerService, CakeService cakeService, CharacteristicService characteristicService, DecorationService decorationService) {
        this.customerService = customerService;
        this.cakeService = cakeService;
        this.characteristicService = characteristicService;
        this.decorationService = decorationService;
    }

    @GetMapping
    public String mainPage(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/hello"; //TODO: exception
        model.addAttribute("customer", customer);
        model.addAttribute("cakes", customer.getCakes());
        return "cakes";
    }

    @GetMapping("/create")
    public String createPage(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/hello";
        model.addAttribute("customer", customer);
        model.addAttribute("cakeBases", cakeService.findAllCakeBases());
        model.addAttribute("characteristics", characteristicService.findAll());
        model.addAttribute("decorations", decorationService.findAll());
        return "create";
    }
}
