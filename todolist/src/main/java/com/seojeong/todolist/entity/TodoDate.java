package com.seojeong.todolist.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToMany(mappedBy = "todoDate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoItem> todoItems = new ArrayList<>();

    public TodoDate(LocalDate date) {
        this.date = date;
    }
}
