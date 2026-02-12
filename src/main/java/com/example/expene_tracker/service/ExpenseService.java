package com.example.expene_tracker.service;

import com.example.expene_tracker.entity.Expense;
import com.example.expene_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

   public  Expense addExpense(Expense expense){
       if(expense.getAmount() <= 0 ){
           throw new RuntimeException("amount must be greater than 0");
       }
       if(expense.getCategory() == null
       ){
           throw new RuntimeException("category must not be null");
       }
       if(!expense.getCategory().trim().equalsIgnoreCase("income") &&
               !expense.getCategory().trim().equalsIgnoreCase("expense")){
           throw new RuntimeException("category must not be income or expense");
       }
       return expenseRepository.save(expense);
   }
   // get fetching all the record from db

   public List<Expense> getExpenses(){
      return expenseRepository.findAll();
   }

   //get fetching single record from db

    public Optional<Expense> getExpenseById(Long id){
           return expenseRepository.findById(id);
    }
}
