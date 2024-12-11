package org.example.skillbox_mod7;

import org.example.skillbox_mod7.domain.Task;
import org.example.skillbox_mod7.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @GetMapping
    public Flux<Task> getAllTasks() {
        return taskService.findAllWithUsers();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Task>> getTaskById(@PathVariable String id) {
        return taskService.findByIdWithUsers(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping
    public Mono<ResponseEntity<Task>> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO)
            .map(ResponseEntity::ok)
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Task>> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_MANAGER')")
    @PutMapping("/{taskId}/observers/{observerId}")
    public Mono<ResponseEntity<Task>> addObserver(@PathVariable String taskId, @PathVariable String observerId) {
        return taskService.addObserver(taskId, observerId)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteTask(@PathVariable String id) {
        return taskService.deleteById(id)
            .map(success -> success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
