package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class RestControllers {

    private final TodoService service;

    public RestControllers(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
    	System.out.println("asdasddddddddd");
    	
    	Todo td=service.findById(id);
    	if(td==null) {
    		return ResponseEntity.noContent().build();
    	}
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return service.create(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        return service.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
    	System.out.println("askdasdas");
        service.delete(id);
        return "Todo deleted successfully";
    }
}
