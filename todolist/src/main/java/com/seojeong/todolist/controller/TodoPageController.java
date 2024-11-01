package com.seojeong.todolist.controller;

import com.seojeong.todolist.dto.TodoResponseDto;
import com.seojeong.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoPageController {

    private final TodoService todoService;

    @GetMapping("/calendar")
    public String viewCalendarPage() {
        return "calendar"; // calendar.html 템플릿 렌더링
    }

    @GetMapping("/todos/{date}")
    public String viewTodosForDay(@PathVariable("date") String date, Model model) {
        LocalDate selectedDate = LocalDate.parse(date); // URL에서 날짜를 파싱하여 LocalDate로 변환
        List<TodoResponseDto> todos = todoService.getTodosForDate(selectedDate);
        model.addAttribute("todos", todos);
        model.addAttribute("date", selectedDate);
        return "todos"; // todos.html 템플릿 렌더링
    }
}
