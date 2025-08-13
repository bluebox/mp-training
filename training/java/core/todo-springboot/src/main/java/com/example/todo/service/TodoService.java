
package com.example.todo.service;

import com.example.todo.exception.NotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepo;

    public TodoService(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    public Todo findById(Long id) {
        Todo todo = todoRepo.findById(id);
        if (todo == null) {
            throw new NotFoundException("Todo with id " + id + " not found");
        }
        return todo;
    }

    public Todo create(Todo todo) {
        return todoRepo.save(todo);
    }

    public Todo update(Long id, Todo todo) {
        Todo existing = findById(id);
        todo.setId(existing.getId());
        return todoRepo.save(todo);
    }

    public void delete(Long id) {
        findById(id); 
        todoRepo.deleteById(id);
        
    }
}
