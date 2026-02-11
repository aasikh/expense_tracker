package com.example.expene_tracker.repository;

import com.example.expene_tracker.entity.Expense;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {


}
