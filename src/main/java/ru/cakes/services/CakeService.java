package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.*;
import ru.cakes.repositories.CakeBaseRepository;
import ru.cakes.repositories.CakeRepository;

import java.util.Set;

@Service
public class CakeService {

    private final CakeRepository cakeRepository;
    private final CakeBaseRepository cakeBaseRepository;

    public CakeService(CakeRepository cakeRepository, CakeBaseRepository cakeBaseRepository) {
        this.cakeRepository = cakeRepository;
        this.cakeBaseRepository = cakeBaseRepository;
    }

    public Cake save(Cake cake) {
        return cakeRepository.save(cake);
    }

    public Iterable<CakeBase> findAllCakeBases() {
        return cakeBaseRepository.findAll();
    }

    public Cake createLocalCake(String name, CakeBase cakeBase, Customer customer, Set<Decoration> decorations, Set<Characteristic> characteristics) {
        Cake cake = new Cake(name, calcCakePrice(decorations), customer);
        cake.setDecorations(decorations);
        cake.setCharacteristics(characteristics);
        cake.setCakeBase(cakeBase);
        return cake;
    }

    public Float calcCakePrice(Set<Decoration> decorations) {
        float decsPrice = 0F;
        if (decorations != null) decsPrice = (float) decorations.stream().mapToDouble(Decoration::getPrice).sum();
        return decsPrice + 500F;
    }

    public boolean isCakeValid(Cake cake) {
        return (cake.getName() != null && cake.getCakeBase() != null && cake.getPrice() >= 500F && cake.getCustomer() != null);
    }

    public Iterable<Cake> findAll() {
        return cakeRepository.findAll();
    }

    public Integer countCustomer(Integer id) {
        return cakeRepository.countCustomerByCustomer_Id(id);
    }

    public Integer countCakeBase(Integer id){
        return cakeRepository.countCakeBaseByCakeBase_id(id);
    }
}
