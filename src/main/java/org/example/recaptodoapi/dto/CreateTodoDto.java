package org.example.recaptodoapi.dto;

import org.example.recaptodoapi.model.TodoStatus;

public record CreateTodoDto(String description, TodoStatus status) {
}
