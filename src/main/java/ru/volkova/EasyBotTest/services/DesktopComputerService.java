package ru.volkova.EasyBotTest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.DesktopComputer;
import org.springframework.stereotype.Service;
import ru.volkova.EasyBotTest.repositories.DesktopComputerRepository;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesktopComputerService {

    private final DesktopComputerRepository desktopComputerRepository;

    @Transactional
    public void save(DesktopComputer computer){
        desktopComputerRepository.save(computer);
    }

    @Transactional
    public void update(Integer id, DesktopComputer computer){
        computer.setId(id);
        desktopComputerRepository.save(computer);
    }

    public DesktopComputer findById(Integer id){
        return desktopComputerRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<DesktopComputer> findAll(){
        return desktopComputerRepository.findAll();
    }
}
