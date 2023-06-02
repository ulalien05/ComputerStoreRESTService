package ru.volkova.EasyBotTest.repositories;

import ru.volkova.EasyBotTest.models.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardDriveRepository extends JpaRepository<HardDrive, Integer> {
}
