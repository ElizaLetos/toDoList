package com.todo.toDoList.controller;

import com.todo.toDoList.model.Point;
import com.todo.toDoList.model.ToDoList;
import com.todo.toDoList.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/lists")
    public ToDoList createList(@RequestParam String name) {
        return toDoService.createList(name);
    }

    @PostMapping("/lists/{listId}/points")
    public Point addPoint(@PathVariable Long listId, @RequestParam String description) {
        return toDoService.addPoint(listId, description);
    }

    @DeleteMapping("/points/{pointId}")
    public void deletePoint(@PathVariable Long pointId) {
        toDoService.deletePoint(pointId);
    }

    @DeleteMapping("/lists/{listId}")
    public void deleteList(@PathVariable Long listId) {
        toDoService.deleteList(listId);
    }

    @GetMapping("/lists")
    public List<ToDoList> getAllLists() {
        return toDoService.getAllLists();
    }

    @GetMapping("/lists/{listId}/points")
    public List<Point> getAllPoints(@PathVariable Long listId) {
        return toDoService.getAllPoints(listId);
    }

    @PutMapping("/points/{pointId}/check")
    public Point markPointAsChecked(@PathVariable Long pointId) {
        return toDoService.markPointAsChecked(pointId);
    }
}
