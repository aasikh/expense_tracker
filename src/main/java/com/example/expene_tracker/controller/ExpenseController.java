package com.example.expene_tracker.controller;
import org.springframework.web.bind.annotation.*;


import com.example.expene_tracker.entity.Expense;
import com.example.expene_tracker.service.ExpenseService;

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

}
