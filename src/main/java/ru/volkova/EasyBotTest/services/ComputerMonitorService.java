package ru.volkova.EasyBotTest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.ComputerMonitor;
import org.springframework.stereotype.Service;
import ru.volkova.EasyBotTest.repositories.ComputerMonitorRepository;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerMonitorService {

    private final ComputerMonitorRepository computerMonitorRepository;

    @Transactional
    public void save(ComputerMonitor computerMonitor){
        computerMonitorRepository.save(computerMonitor);
    }

    @Transactional
    public void update(Integer id, ComputerMonitor computerMonitor){
        computerMonitor.setId(id);
        computerMonitorRepository.save(computerMonitor);
    }

    public ComputerMonitor findById(Integer id){
        return computerMonitorRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<ComputerMonitor> findAll(){
        return computerMonitorRepository.findAll();
    }
}
