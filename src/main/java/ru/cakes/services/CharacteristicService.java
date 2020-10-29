package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.Characteristic;
import ru.cakes.repositories.CharacteristicRepository;

@Service
public class CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicService(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public Iterable<Characteristic> findAll() {
        return characteristicRepository.findAll();
    }

}
