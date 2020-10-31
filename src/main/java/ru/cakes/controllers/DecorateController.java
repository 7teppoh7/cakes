package ru.cakes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.cakes.decorators.*;
import ru.cakes.entities.Cake;
import ru.cakes.entities.Customer;
import ru.cakes.services.CakeService;
import ru.cakes.services.CustomerService;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/{customerId}/decorate")
@SessionAttributes({"name", "cherryDecoration", "korzhCakeBase", "candleDecoration"})
public class DecorateController {

    private final CustomerService customerService;
    private final CakeService cakeService;

    public DecorateController(CustomerService customerService, CakeService cakeService) {
        this.customerService = customerService;
        this.cakeService = cakeService;
    }

    @GetMapping
    public String decorate(HttpSession session, SessionStatus ses, Model model, @PathVariable Integer customerId,
                           @RequestParam(required = false) Boolean create,
                           @RequestParam(required = false) Boolean agree) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";
        model.addAttribute("customer", customer);

        String name = (String) session.getAttribute("name");
        Boolean korzhCakeBase = (Boolean) session.getAttribute("korzhCakeBase");
        Boolean cherryDecoration = (Boolean) session.getAttribute("cherryDecoration");
        Boolean candleDecoration = (Boolean) session.getAttribute("candleDecoration");

        CakeDecorator cakeDecorator = new CakeContainer(new Cake());
        if (name != null) cakeDecorator = new NameDecorator(cakeDecorator, name);
        if (korzhCakeBase != null && korzhCakeBase) cakeDecorator = new KorzhCakeBase(cakeDecorator);
        if (cherryDecoration != null && cherryDecoration) cakeDecorator = new CherryDecoration(cakeDecorator);
        if (candleDecoration != null && candleDecoration) cakeDecorator = new CandleDecoration(cakeDecorator);

        cakeDecorator.decorate();
        Cake cake = cakeDecorator.getCake();

        cake.setCustomer(customer);
        cake.setPrice(cakeService.calcCakePrice(cake.getDecorations()));

        if (create != null) {
            if (create) { //пользователь выбрал "Создать"
                if (cakeService.isCakeValid(cake)) {
                    if (agree != null) { //пользователь посетил "buy"
                        if (agree) {
                            cakeService.save(cake);
                            session.invalidate();
                            ses.setComplete();
                            return "redirect:/{customerId}";
                        }
                        session.invalidate();
                        ses.setComplete();
                        return "redirect:/{customerId}/decorate";
                    }
                    model.addAttribute("customer", customer);
                    model.addAttribute("cake", cake);
                    return "buy";
                }
                return "redirect:/{customerId}/decorate";
            } //выбор пользователя "Очистить"
            session.invalidate();
            ses.setComplete();
            return "redirect:/{customerId}/decorate";
        }
        return "decorationList";
    }

    @GetMapping("/korzhCakeBase")
    public String korzhCakeBase(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";
        model.addAttribute("korzhCakeBase", true);
        return "redirect:/{customerId}/decorate";
    }


    @GetMapping("/cherryDecoration")
    public String cherryDecoration(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";
        model.addAttribute("cherryDecoration", true);
        return "redirect:/{customerId}/decorate";
    }

    @GetMapping("/candleDecoration")
    public String candleDecoration(Model model, @PathVariable Integer customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";
        model.addAttribute("candleDecoration", true);
        return "redirect:/{customerId}/decorate";
    }

    @GetMapping("/name")
    public String name(Model model, @PathVariable Integer customerId, @RequestParam String value) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) return "redirect:/";

        if (value != null) {
            value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8); //для поддержки кириллицы
            if (!value.trim().equals("")) {
                model.addAttribute("name", value);
            }
        }
        return "redirect:/{customerId}/decorate";
    }
}
