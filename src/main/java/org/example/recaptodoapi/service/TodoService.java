package org.example.recaptodoapi.service;

import org.example.recaptodoapi.dto.CreateTodoDto;
import org.example.recaptodoapi.dto.TodoDto;
import org.example.recaptodoapi.exception.TodoNotFoundException;
import org.example.recaptodoapi.model.Todo;
import org.example.recaptodoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repo;
    private final IdService idService;

    public TodoService(TodoRepository repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
    }

    // Helper:
    private TodoDto convertToDto(Todo todo) {
        return new TodoDto(todo.id(), todo.description(), todo.status());
    }

    public List<TodoDto> getTodos() {
        return repo.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public TodoDto createTodo(CreateTodoDto dto) {
        String id = idService.generateId();
        Todo todo = new Todo(id, dto.description(), dto.status());
        todo = repo.save(todo);
        return convertToDto(todo);
    }

    public TodoDto getTodoById(String id) {
        Todo todo = repo.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        return convertToDto(todo);
    }

    public TodoDto updateTodoById(String id, TodoDto dto) {
        repo.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        Todo newTodo = new Todo(id, dto.description(), dto.status());
        return convertToDto(repo.save(newTodo));
    }

    public TodoDto deleteTodo(String id) {
        Todo oldTodo = repo.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        repo.deleteById(id);
        return convertToDto(oldTodo);
    }
}
