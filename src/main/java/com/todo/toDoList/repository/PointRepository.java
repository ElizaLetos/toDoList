package com.todo.toDoList.repository;

import com.todo.toDoList.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
