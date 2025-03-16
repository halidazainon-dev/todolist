package com.application.todolist.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.application.todolist.dao.TaskDAO;
import com.application.todolist.vo.Task;

@WebMvcTest(TaskService.class)
public class TaskServiceTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private TaskDAO taskDAO; 

    @Test
    public void testHomeRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))   
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks")); 
    }

    @Test
    public void testGetTasks() throws Exception {
        List<Task> tasks = Arrays.asList(
            new Task(1, "Task 1", false, null),
            new Task(2, "Task 2", true, null)
        );
        when(taskDAO.listAll()).thenReturn(tasks);
        when(taskDAO.activeCount()).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())   
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))  
                .andExpect(MockMvcResultMatchers.model().attribute("activeCount", 1))
                .andExpect(MockMvcResultMatchers.view().name("todo"));   
    }

    @Test
    public void testAddTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .param("description", "New Task") 
                        .param("completed", "false")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks"));  

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskDAO, times(1)).addTask(taskCaptor.capture());

        Task capturedTask = taskCaptor.getValue();
        assert capturedTask.getDescription().equals("New Task");
        assert !capturedTask.isCompleted();
        assert capturedTask.getCreatedDate() != null;
    }

    @Test
    public void testDeleteTask() throws Exception {
        int taskId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  
                .andExpect(MockMvcResultMatchers.content().string("Task deleted successfully"));  

        // Verify that deleteTask method is called once with the correct task ID
        verify(taskDAO, times(1)).deleteTask(taskId);
    }

    @Test
    public void testUpdateTask() throws Exception {
        int taskId = 1;
        String newDescription = "Updated Task Description";
        Task existingTask = new Task(taskId, "Old Description", false, new Date());

        when(taskDAO.getId(taskId)).thenReturn(existingTask);

        mockMvc.perform(MockMvcRequestBuilders.post("/update")
                        .param("id", String.valueOf(taskId))
                        .param("description", newDescription)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks"));  

        // Verify method calls
        verify(taskDAO, times(1)).getId(taskId);
        verify(taskDAO, times(1)).updateTask(existingTask);

        assert(existingTask.getDescription().equals(newDescription));
    }

    @Test
    public void testUpdateTaskStatus() throws Exception {
        int taskId = 1;
        boolean completedStatus = true;
        Task existingTask = new Task(taskId, "Sample Task", false, new Date());

        when(taskDAO.getId(taskId)).thenReturn(existingTask);

        mockMvc.perform(MockMvcRequestBuilders.post("/updateTaskStatus")
                        .param("id", String.valueOf(taskId))
                        .param("completed", String.valueOf(completedStatus))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks"));  

        verify(taskDAO, times(1)).getId(taskId);
        verify(taskDAO, times(1)).updateTask(existingTask);

        assert(existingTask.isCompleted() == completedStatus);
    }

    @Test
    public void testDeleteAllTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteAll")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks")); // Redirects to /tasks

        verify(taskDAO, times(1)).deleteAll();
    }

}
