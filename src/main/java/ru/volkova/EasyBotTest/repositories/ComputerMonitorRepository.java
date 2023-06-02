package ru.volkova.EasyBotTest.repositories;

import ru.volkova.EasyBotTest.models.ComputerMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerMonitorRepository extends JpaRepository<ComputerMonitor, Integer> {
}
