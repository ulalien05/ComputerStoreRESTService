package ru.volkova.EasyBotTest.repositories;

import ru.volkova.EasyBotTest.models.DesktopComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Integer> {
}
