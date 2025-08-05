package ru.academy.homework.jdbcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.academy.homework.jdbcdemo.entity.Worker;
import ru.academy.homework.jdbcdemo.service.WorkerService;

@Controller
@RequestMapping("/workers")
public class WorkerViewController {

    private final WorkerService workerService;

    public WorkerViewController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public String index(
            @RequestParam(required = false) String name,
            Model model) {

        model.addAttribute("workers", workerService.getWorkers(name));
        model.addAttribute("currentFilter", name); // Для отображения в форме
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("worker", new Worker());
        return "create";
    }

    @PostMapping("/create")
    public String createWorker(@ModelAttribute Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }
}