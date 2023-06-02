package ru.volkova.EasyBotTest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.HardDrive;
import org.springframework.stereotype.Service;
import ru.volkova.EasyBotTest.repositories.HardDriveRepository;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardDriveService {

    private final HardDriveRepository hardDriveRepository;

    @Transactional
    public void save(HardDrive hardDrive){
        hardDriveRepository.save(hardDrive);
    }

    @Transactional
    public void update(Integer id, HardDrive hardDrive){
        hardDrive.setId(id);
        hardDriveRepository.save(hardDrive);
    }

    public HardDrive findById(Integer id){
        return hardDriveRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<HardDrive> findAll(){
        return hardDriveRepository.findAll();
    }
}
