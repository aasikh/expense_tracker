package com.example.expene_tracker.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.expene_tracker.entity.Expense;
import com.example.expene_tracker.service.ExpenseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {


    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense){

        return expenseService.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses(){

        return expenseService.getExpenses();
    }

    @GetMapping("/{id}")
    public Optional<Expense> getExpenseById(@PathVariable Long id){

        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense){
       expense.setId(id);
       return expenseService.updateExpenseById(expense);
    }
    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable Long id){
        expenseService.deleteExpenseById(id);
    }
}
