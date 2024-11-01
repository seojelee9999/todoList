package com.seojeong.todolist.controller;

import com.seojeong.todolist.dto.TodoRequestDto;
import com.seojeong.todolist.dto.TodoResponseDto;
import com.seojeong.todolist.entity.TodoItem;
import com.seojeong.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 특정 날짜의 Todo 리스트 조회
    @GetMapping("/{date}")
    public List<TodoResponseDto> getTodosForDate(@PathVariable("date") LocalDate date) {
        return todoService.getTodosForDate(date).stream()
                .map(todo -> new TodoResponseDto(todo.getId(), todo.getTask(), todo.isCompleted()))
                .collect(Collectors.toList());
    }

    // 특정 날짜에 Todo 항목 추가
    @PostMapping
    public TodoResponseDto addTodoForDate(@RequestBody TodoRequestDto request) {
        TodoItem todoItem = todoService.addTodoForDate(request.getDate(), request.getTask(), request.isCompleted());
        return new TodoResponseDto(todoItem.getId(), todoItem.getTask(), todoItem.isCompleted());
    }

    // Todo 항목의 완료 상태 업데이트
    @PutMapping("/{id}")
    public TodoResponseDto updateTodoStatus(@PathVariable Long id, @RequestBody TodoRequestDto request) {
        TodoItem updatedTodo = todoService.updateTodoStatus(id, request.isCompleted());
        return new TodoResponseDto(updatedTodo.getId(), updatedTodo.getTask(), updatedTodo.isCompleted());
    }
}
