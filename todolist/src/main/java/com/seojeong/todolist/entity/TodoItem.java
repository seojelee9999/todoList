package com.seojeong.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "todo_date_id")
    private TodoDate todoDate;

    // 빌더 패턴에서 연관 관계 필드를 포함하기 위해 @Builder.Default 사용
    public static class TodoItemBuilder {
        private TodoDate todoDate;

        public TodoItemBuilder todoDate(TodoDate todoDate) {
            this.todoDate = todoDate;
            return this;
        }
    }
}
