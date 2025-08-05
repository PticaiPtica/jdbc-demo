package ru.academy.homework.jdbcdemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.academy.homework.jdbcdemo.entity.Worker;
import ru.academy.homework.jdbcdemo.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }


    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new RuntimeException("Worker not found with id: " + id);
        }
        workerRepository.deleteById(id);
    }
    public List<Worker> getWorkers(String nameFilter) {
        if (nameFilter != null && !nameFilter.isEmpty()) {
            return workerRepository.findByNameContainingIgnoreCase(nameFilter);
        }
        return workerRepository.findAll();
    }


    public Worker createWorker(Worker worker) {
        if (worker.getName() == null || worker.getName().isBlank()) {
            throw new IllegalArgumentException("Имя работника не может быть пустым");
        }
        if (worker.getSalary() < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }
        return workerRepository.save(worker);
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with id: " + id));
    }

    public Worker updateWorker(Long id, Worker workerDetails) {
        Worker worker = getWorkerById(id);

        if (workerDetails.getName() != null) {
            worker.setName(workerDetails.getName());
        }
        if (workerDetails.getPosition() != null) {
            worker.setPosition(workerDetails.getPosition());
        }
        if (workerDetails.getSalary() != 0) {
            worker.setSalary(workerDetails.getSalary());
        }

        return workerRepository.save(worker);
    }
}