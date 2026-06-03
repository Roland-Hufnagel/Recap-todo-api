package org.example.recaptodoapi.controller;

import org.example.recaptodoapi.dto.CreateTodoDto;
import org.example.recaptodoapi.dto.TodoDto;
import org.example.recaptodoapi.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.status(200).body(service.getTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable String id) {
        TodoDto foundTodoDto = service.getTodoById(id);
        return ResponseEntity.status(200).body(foundTodoDto);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody CreateTodoDto dto) {
        return ResponseEntity.status(201).body(service.createTodo(dto));
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable String id, @RequestBody TodoDto dto) {
        return service.updateTodoById(id, dto);
    }

    @DeleteMapping("/{id}")
    public TodoDto deleteTodo(@PathVariable String id) {
        return service.deleteTodo(id);
    }
}
