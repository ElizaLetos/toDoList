package com.todo.toDoList.service;

import com.todo.toDoList.model.Point;
import com.todo.toDoList.model.ToDoList;
import com.todo.toDoList.repository.PointRepository;
import com.todo.toDoList.repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoListRepository listRepository;
    private final PointRepository pointRepository;

    public ToDoService(ToDoListRepository listRepository, PointRepository pointRepository) {
        this.listRepository = listRepository;
        this.pointRepository = pointRepository;
    }

    public ToDoList createList(String name) {
        ToDoList list = new ToDoList();
        list.setName(name);
        return listRepository.save(list);
    }

    public Point addPoint(Long listId, String description) {
        ToDoList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));
        Point point = new Point();
        point.setDescription(description);
        point.setToDoList(list);
        return pointRepository.save(point);
    }

    public void deletePoint(Long pointId) {
        pointRepository.deleteById(pointId);
    }

    public void deleteList(Long listId) {
        listRepository.deleteById(listId);
    }

    public List<ToDoList> getAllLists() {
        return listRepository.findAll();
    }

    public List<Point> getAllPoints(Long listId) {
        ToDoList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));
        return list.getPoints();
    }

    public Point markPointAsChecked(Long pointId) {
        Point point = pointRepository.findById(pointId)
                .orElseThrow(() -> new RuntimeException("Point not found"));
        point.setChecked(true);
        return pointRepository.save(point);
    }
}
