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

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }


    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
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
                .orElse(null); // Возвращаем null если работник не найден
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