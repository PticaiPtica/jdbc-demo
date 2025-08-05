package ru.academy.homework.jdbcdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.academy.homework.jdbcdemo.entity.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    // Фильтрация по имени (точное совпадение)
    List<Worker> findByName(String name);

    // Фильтрация по части имени (без учета регистра)
    List<Worker> findByNameContainingIgnoreCase(String namePart);

    // Альтернативный вариант с JPQL
    @Query("SELECT w FROM Worker w WHERE LOWER(w.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Worker> searchByName(@Param("namePart") String namePart);
}