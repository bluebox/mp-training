package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }
    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", service.findAll());
        return "todos/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todos/form"; 
    }

    @PostMapping
    public String createTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult br) {
        if (br.hasErrors()) {
            return "todos/form";
        }
        service.create(todo);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.findById(id));
        return "todos/form";
    }

    @PostMapping("/{id}")
    public String updateTodo(@PathVariable Long id, @Valid @ModelAttribute("todo") Todo todo, BindingResult br) {
        if (br.hasErrors()) {
            return "todos/form";
        }
        service.update(id, todo);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/todos";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}

