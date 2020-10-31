package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.Decoration;
import ru.cakes.repositories.DecorationRepository;

@Service
public class DecorationService {

    private final DecorationRepository decorationRepository;

    public DecorationService(DecorationRepository decorationRepository) {
        this.decorationRepository = decorationRepository;
    }

    public Decoration findByName(String name) {
        return decorationRepository.findByName(name);
    }

    public Decoration save(Decoration decoration) {
        return decorationRepository.save(decoration);
    }

    public Iterable<Decoration> findAll() {
        return decorationRepository.findAll();
    }

    public Decoration findById(Integer id) {
        return decorationRepository.findById(id).orElse(null);
    }

    public Integer countDecoration(Integer id){
        return decorationRepository.countById(id);
    }
}
