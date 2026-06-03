package org.example.recaptodoapi.dto;

import org.example.recaptodoapi.model.TodoStatus;

public record TodoDto(String id, String description, TodoStatus status) {
}
