package com.example.expene_tracker.repository;

import com.example.expene_tracker.entity.Expense;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {

List<Expense> findByUserId(Long userId);
Optional<Expense> findByIdAndUserId(Long expenseId, Long id);
Optional<Expense> deleteByIdAndUserId(Long expenseId, Long id);

}
