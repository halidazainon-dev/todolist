package com.application.todolist.service;

import com.application.todolist.dao.TaskDAO;
import com.application.todolist.vo.Task;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        taskDAO.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam("id") int id, @RequestParam("description") String description) {
        Task task = taskDAO.getId(id);
        task.setDescription(description);
        task.setCreatedDate(new Date());

        taskDAO.updateTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/updateTaskStatus")
        public String updateTaskStatus(@RequestParam("id") int id, @RequestParam("completed") boolean completed) {
        Task task = taskDAO.getId(id);
        task.setCompleted(completed);
        taskDAO.updateTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/deleteAll")
    public String deleteAllTasks() {
        taskDAO.deleteAll();
        return "redirect:/tasks";
    }
}
