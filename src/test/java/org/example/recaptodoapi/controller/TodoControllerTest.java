package org.example.recaptodoapi.controller;

import org.example.recaptodoapi.model.Todo;
import org.example.recaptodoapi.model.TodoStatus;
import org.example.recaptodoapi.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepository repo;

    @Test
    void getAllTodos_shouldReturnListOfOneTodo_whenCalled() throws Exception {
        // GIVEN:
        Todo todo = new Todo("1", "Buy milk", TodoStatus.OPEN);
        repo.save(todo);
        // WHEN + THEN:
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                  {
                                    "id": "1",
                                    "description": "Buy milk",
                                    "status": "OPEN"
                                  }
                                ]
                                
                                """
                ));
    }
}