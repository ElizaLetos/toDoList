package com.todo.toDoList.repository;

import com.todo.toDoList.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
