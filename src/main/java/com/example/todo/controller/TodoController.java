package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos") // Base path for all endpoints
@RequiredArgsConstructor // Lombok: Injects service
// Enable Cross-Origin Resource Sharing (CORS) for all origins.
// This allows our HTML file (on a different "origin") to call the API.
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    // GET /api/todos - Get all tasks
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // POST /api/todos - Create a new task
    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        // @Valid triggers validation (e.g., @NotBlank)
        // @RequestBody maps the JSON from the request to the Todo object
        return todoService.createTodo(todo);
    }

    // PUT /api/todos/{id} - Update an existing task
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoDetails) {
        return todoService.updateTodo(id, todoDetails);
    }

    // DELETE /api/todos/{id} - Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        // Return 200 OK with no content
        return ResponseEntity.ok().build();
    }
}