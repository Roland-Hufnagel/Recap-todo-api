package org.example.recaptodoapi.service;

import org.example.recaptodoapi.dto.CreateTodoDto;
import org.example.recaptodoapi.dto.TodoDto;
import org.example.recaptodoapi.model.Todo;
import org.example.recaptodoapi.model.TodoStatus;
import org.example.recaptodoapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TodoServiceTest {
    private TodoRepository mockRepo;
    private IdService mockIdService;
    private TodoService testService;

    @BeforeEach
    void setup() {
        mockRepo = mock(TodoRepository.class);
        mockIdService = mock(IdService.class);
        testService = new TodoService(mockRepo, mockIdService);
    }


    // getTodos teste ich nicht, weil sie nur delegiert

    @Test
    void getTodoById_shouldThrowException_whenCalledWithInvalidId() {
        // GIVEN:
        when(mockRepo.findById("fail-id")).thenReturn(Optional.empty());
        // WHEN:
        // THEN:
        assertThrows(NoSuchElementException.class, () -> testService.getTodoById("fail-id"));
    }

    @Test
    void getTodoById_shouldReturnCorrectDTO() {
        // GIVEN:
        Todo todo = new Todo("123", "Clean Car", TodoStatus.OPEN);
        when(mockRepo.findById("123")).thenReturn(Optional.of(todo));
        TodoDto expectedResponseDto = new TodoDto("123", "Clean Car", TodoStatus.OPEN);
        // WHEN:
        TodoDto actual = testService.getTodoById("123");
        // THEN:
        assertEquals(expectedResponseDto, actual);

    }

    @Test
    void createTodo_shouldReturnDtoWithCorrectData() {
        // GIVEN:
        CreateTodoDto input = new CreateTodoDto("Buy Milk", TodoStatus.OPEN);
        Todo savedTodo = new Todo("generated-id-123", "Buy Milk", TodoStatus.OPEN);

        when(mockIdService.generateId()).thenReturn("generated-id-123");
        when(mockRepo.save(any(Todo.class))).thenReturn(savedTodo);

        // WHEN:
        TodoDto result = testService.createTodo(input);

        // THEN:
        assertEquals("generated-id-123", result.id());
        assertEquals("Buy Milk", result.description());
        assertEquals(TodoStatus.OPEN, result.status());
        verify(mockRepo, times(1)).save(any(Todo.class));
        verify(mockIdService, times(1)).generateId();
    }
}