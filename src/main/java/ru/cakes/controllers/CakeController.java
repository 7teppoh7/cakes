package ru.cakes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.cakes.entities.Cake;
import ru.cakes.entities.Characteristic;
import ru.cakes.entities.Customer;
import ru.cakes.entities.Decoration;
import ru.cakes.services.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/{customerId}")
public class CakeController {

    private final CustomerService customerService;
    private final CakeService cakeService;
    private final CharacteristicService characteristicService;
    private final DecorationService decorationService;
    private final CakeBaseService cakeBaseService;

    public CakeController(CustomerService customerService, CakeService cakeService, CharacteristicService characteristicService, DecorationService decorationService, CakeBaseService cakeBaseService) {
        this.customerService = customerService;
        this.cakeService = cakeService;
        this.characteristicService = characteristicService;
        this.decorationService = decorationService;
        this.cakeBaseService = cakeBaseService;
    }

    @GetMapping
    public String mainPage(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/"; //TODO: exception
        model.addAttribute("customer", customer);
        model.addAttribute("cakes", customer.getCakes());
        return "cakes";
    }

    @GetMapping("/create")
    public String createPage(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";
        model.addAttribute("customer", customer);
        model.addAttribute("cakeBases", cakeService.findAllCakeBases());
        model.addAttribute("characteristics", characteristicService.findAll());
        model.addAttribute("decorations", decorationService.findAll());
        model.addAttribute("cakesAll", cakeService.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String priceCalc(Model model, @PathVariable Integer customerId,
                            @RequestParam String name, @RequestParam Integer cakeBase,
                            @RequestParam(required = false) Set<Integer> decorations,
                            @RequestParam(required = false) Set<Integer> characteristics,
                            @RequestParam(required = false) Boolean agree) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";

        Set<Decoration> decList = new HashSet<>();
        Set<Characteristic> chaSet = new HashSet<>();
        if (decorations != null)
            decList = decorations.stream().map(decorationService::findById).filter(Objects::nonNull).collect(Collectors.toSet());
        if (characteristics != null)
            chaSet = characteristics.stream().map(characteristicService::findById).filter(Objects::nonNull).collect(Collectors.toSet());

        Cake localCake = cakeService.createLocalCake(name, cakeBaseService.findById(cakeBase), customer, decList, chaSet);
        if (cakeService.isCakeValid(localCake)) {
            if (agree != null) { //пользователь посетил "buy"
                if (agree) {
                    cakeService.save(localCake);
                    return "redirect:/{customerId}";
                }
                return "redirect:/{customerId}/create";
            }
            model.addAttribute("customer", customer);
            model.addAttribute("cake", localCake);
            return "buy";
        }
        return "redirect:/{customerId}/create";
    }

}
