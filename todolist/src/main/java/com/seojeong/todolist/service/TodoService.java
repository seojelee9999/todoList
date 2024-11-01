package com.seojeong.todolist.service;

import com.seojeong.todolist.dto.TodoResponseDto;
import com.seojeong.todolist.entity.TodoDate;
import com.seojeong.todolist.entity.TodoItem;
import com.seojeong.todolist.repository.TodoDateRepository;
import com.seojeong.todolist.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDateRepository todoDateRepository;
    private final TodoRepository todoRepository;

    // 특정 날짜의 Todo 항목을 조회하여 TodoResponseDto 리스트로 반환
    public List<TodoResponseDto> getTodosForDate(LocalDate date) {
        // 해당 날짜의 TodoDate를 조회하고, 없으면 생성
        TodoDate todoDate = todoDateRepository.findByDate(date)
                .orElseGet(() -> createTodoDate(date));

        // TodoItem을 TodoResponseDto로 변환하여 반환
        return todoDate.getTodoItems().stream()
                .map(todoItem -> new TodoResponseDto(todoItem.getId(), todoItem.getTask(), todoItem.isCompleted()))
                .collect(Collectors.toList());
    }


    // 특정 날짜에 Todo 항목을 추가
    public TodoItem addTodoForDate(LocalDate date, String task, boolean completed) {
        TodoDate todoDate = todoDateRepository.findByDate(date)
                .orElseGet(() -> createTodoDate(date));

        TodoItem newTodo = TodoItem.builder()
                .task(task)
                .completed(completed)
                .todoDate(todoDate)
                .build();

        todoDate.getTodoItems().add(newTodo);
        todoDateRepository.save(todoDate); // 연관된 TodoItem도 자동 저장
        return newTodo;
    }

    // 새로운 날짜의 TodoDate 생성
    private TodoDate createTodoDate(LocalDate date) {
        TodoDate todoDate = new TodoDate();
        todoDate.setDate(date);
        return todoDateRepository.save(todoDate);
    }

    public TodoItem updateTodoStatus(Long id, boolean completed) {
        TodoItem todoItem = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TodoItem not found with id: " + id));

        // 완료 상태 업데이트
        todoItem.setCompleted(completed);
        return todoRepository.save(todoItem);
    }
}
