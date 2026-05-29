package org.example.recaptodoapi.service;

import org.example.recaptodoapi.dto.TodoDto;
import org.example.recaptodoapi.model.Todo;
import org.example.recaptodoapi.model.TodoStatus;
import org.example.recaptodoapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

TodoRepository mockRepo;

class TodeServiceTest {
    @BeforeEach
    void setup(){
    mockRepo  = mock(TodoRepository.class);
            }


    // getTodos teste ich nicht, weil sie nur delegiert...

    @Test
    void createTodo_schouldReturnDtoWithCorrectData() {
        // GIVEN:
        TodoDto input = new TodoDto("Buy Milk", TodoStatus.OPEN);
        Todo savedTodo = new Todo("generated-id-123", "Buy Milk", TodoStatus.OPEN);

        when(idService.generateId())
    }
}