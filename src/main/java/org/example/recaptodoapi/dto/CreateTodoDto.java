package org.example.recaptodoapi.dto;

import org.example.recaptodoapi.model.TodoStatus;

public record ResponseDto(String id, String description, TodoStatus status) {
}
