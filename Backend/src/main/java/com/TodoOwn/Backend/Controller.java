    package com.TodoOwn.Backend;

    import lombok.RequiredArgsConstructor;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RequiredArgsConstructor
    @CrossOrigin(origins = "*")
    @RestController
    public class Controller {
        private final Services services;
        @PostMapping("/addTask")
        void addTask(@RequestParam String title){
            services.addTask(title);
        }

        @DeleteMapping("/delete/{id}")
        void deleteTask(@PathVariable Long id){
            services.deleteTask(id);
        }

        @GetMapping("/getTasks")
        List<Models> getTasks(){
            return services.getTasks();
        }

        @PutMapping("/toggle/{id}")
        void toggleTask(@PathVariable Long id){
            services.toggleTask(id);
        }

        @PutMapping("updateTitle/{id}/{title}")
        void updateTitle(@PathVariable Long id, @PathVariable String title){
            services.updateTitle(id , title);
        }

        @DeleteMapping("/clear-completed")
        void clearCompleted() {
            services.clearCompletedTasks();
        }

    }
