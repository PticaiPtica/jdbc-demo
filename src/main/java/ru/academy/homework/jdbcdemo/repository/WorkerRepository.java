package ru.academy.homework.jdbcdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.homework.jdbcdemo.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}