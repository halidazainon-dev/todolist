package com.application.todolist.service;

import com.application.todolist.dao.TaskDAO;
import com.application.todolist.vo.Task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping("/")  
    public String home() {
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskDAO.listAll();
        int activeCount = taskDAO.activeCount();

        model.addAttribute("tasks", tasks);
        model.addAttribute("activeCount", activeCount);
        return "todo";  
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        task.setCreatedDate(new Date());
        taskDAO.addTask(task);
        return "redirect:/tasks";
    }

    
}
