package com.application.todolist.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

}
