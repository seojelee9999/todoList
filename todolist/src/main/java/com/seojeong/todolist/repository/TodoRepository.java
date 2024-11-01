package com.seojeong.todolist.repository;


import com.seojeong.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
}
