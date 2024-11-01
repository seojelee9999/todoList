package com.seojeong.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String task;
    private boolean completed;
}
