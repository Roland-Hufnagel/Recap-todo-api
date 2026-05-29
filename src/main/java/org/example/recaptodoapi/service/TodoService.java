package org.example.recaptodoapi.service;

import org.example.recaptodoapi.dto.TodoDto;
import org.example.recaptodoapi.model.Todo;
import org.example.recaptodoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodeService {
    private final TodoRepository repo;
    private final IdService idService;

    public TodeService(TodoRepository repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
    }

    // Helper:
    private TodoDto convertToDto(Todo todo) {
        return new TodoDto(todo.description(), todo.status());
    }

    public List<Todo> getTodos() {
        return repo.findAll();
    }

    public TodoDto createTodo(TodoDto dto) {
        String id = idService.generateId();
        Todo todo = new Todo(id, dto.description(), dto.status());
        todo = repo.save(todo);
        return convertToDto(todo);
    }
}
