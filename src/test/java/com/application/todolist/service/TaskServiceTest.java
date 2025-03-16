package com.application.todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.application.todolist.dao.TaskDAO;

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

}
