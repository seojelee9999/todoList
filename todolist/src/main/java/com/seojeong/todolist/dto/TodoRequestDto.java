package com.seojeong.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    private String task;
    private boolean completed;
    private LocalDate date; // 특정 날짜에 Todo 추가를 위한 필드
}
