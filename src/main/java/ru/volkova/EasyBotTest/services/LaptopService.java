package ru.volkova.EasyBotTest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.Laptop;
import org.springframework.stereotype.Service;
import ru.volkova.EasyBotTest.repositories.LaptopRepository;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaptopService {

    private final LaptopRepository laptopRepository;

    @Transactional
    public void save(Laptop laptop){
        laptopRepository.save(laptop);
    }

    @Transactional
    public void update(Integer id, Laptop laptop){
        laptop.setId(id);
        laptopRepository.save(laptop);
    }

    public Laptop findById(Integer id){
        return laptopRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }
}
