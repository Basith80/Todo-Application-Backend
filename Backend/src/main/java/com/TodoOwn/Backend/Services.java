package com.TodoOwn.Backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Services {

    private final Repository todoRepository;

    public void addTask(String title) {
        Models models = new Models(title, false);
        todoRepository.save(models);
    }

    public List<Models> getTasks() {
        return todoRepository.findAll();
    }


    public void toggleTask(Long id) {
        // Use findById for safety in 2025 Spring Boot versions
        Models model = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        model.setDone(!model.isDone());
        todoRepository.save(model);
    }

    public void deleteTask(Long id) {
        todoRepository.deleteById(id);
    }

    public void updateTitle( Long id, String title) {
        Models model = todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Input "+id));
        model.setTitle(title);
        todoRepository.save(model);
    }

    public void clearCompletedTasks() {
        todoRepository.deleteByDone(true);
    }

}
