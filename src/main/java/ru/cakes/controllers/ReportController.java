package ru.cakes.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cakes.services.*;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final CakeService cakeService;
    private final DecorationService decorationService;
    private final CharacteristicService characteristicService;
    private final CakeBaseService cakeBaseService;
    private final CustomerService customerService;

    public ReportController(CakeService cakeService, DecorationService decorationService, CharacteristicService characteristicService, CakeBaseService cakeBaseService, CustomerService customerService) {
        this.cakeService = cakeService;
        this.decorationService = decorationService;
        this.characteristicService = characteristicService;
        this.cakeBaseService = cakeBaseService;
        this.customerService = customerService;
    }

    @GetMapping
    public String reportList() {
        return "reportList";
    }

    @GetMapping("/topDecorations")
    public String topDecorations(Model model) {
        Set<ReportItem> reportItems = new TreeSet<>((x, y) -> y.getPopularity() - x.getPopularity());
        reportItems.addAll(StreamSupport.stream(decorationService.findAll().spliterator(), false)
                .map((x) -> new ReportItem(x.getId(), x.getName() + " (" + x.getPrice() + ")", decorationService.countDecoration(x.getId())))
                .collect(Collectors.toSet()));
        model.addAttribute("reportItems", reportItems);
        model.addAttribute("reportName", "Декорации");
        return "report";
    }

    @GetMapping("/topCharacteristics")
    public String topCharacteristics(Model model) {
        Set<ReportItem> reportItems = new TreeSet<>((x, y) -> y.getPopularity() - x.getPopularity());
        reportItems.addAll(StreamSupport.stream(characteristicService.findAll().spliterator(), false)
                .map((x) -> new ReportItem(x.getId(), x.getName(), characteristicService.countCharacteristic(x.getId())))
                .collect(Collectors.toSet()));
        model.addAttribute("reportItems", reportItems);
        model.addAttribute("reportName", "Характеристики");
        return "report";
    }

    @GetMapping("/topCakeBases")
    public String topCakeBases(Model model) {
        Set<ReportItem> reportItems = new TreeSet<>((x, y) -> y.getPopularity() - x.getPopularity());
        reportItems.addAll(StreamSupport.stream(cakeBaseService.findAll().spliterator(), false)
                .map((x) -> new ReportItem(x.getId(), x.getName(), cakeService.countCakeBase(x.getId())))
                .collect(Collectors.toSet()));
        model.addAttribute("reportItems", reportItems);
        model.addAttribute("reportName", "Основы торта");
        return "report";
    }

    @GetMapping("/topCustomers")
    public String topCustomers(Model model) {
        Set<ReportItem> reportItems = new TreeSet<>((x, y) -> y.getPopularity() - x.getPopularity());
        reportItems.addAll(StreamSupport.stream(customerService.findAll().spliterator(), false)
                .map((x) -> new ReportItem(x.getId(), x.getFirstName() + " " + x.getLastName(), cakeService.countCustomer(x.getId())))
                .collect(Collectors.toSet()));
        model.addAttribute("reportItems", reportItems);
        model.addAttribute("reportName", "Покупатели");
        return "report";
    }

    @Data
    @AllArgsConstructor
    private class ReportItem {
        private final Integer id;
        private final String name;
        private final Integer popularity;
    }

}
