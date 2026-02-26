package com.example.expene_tracker.service;
import com.example.expene_tracker.entity.User;
import com.example.expene_tracker.entity.Expense;
import com.example.expene_tracker.repository.ExpenseRepository;
import com.example.expene_tracker.repository.UserRepositroy;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final UserRepositroy userRepositroy;

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense) {
        if (expense.getAmount() <= 0) {
            throw new RuntimeException("amount must be greater than 0");
        }
        if (expense.getCategory() == null
        ) {
            throw new RuntimeException("category must not be null");
        }
        if (!expense.getCategory().trim().equalsIgnoreCase("income") &&
                !expense.getCategory().trim().equalsIgnoreCase("expense")) {
            throw new RuntimeException("category must not be income or expense");
        }
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = Long.parseLong(userId);
        User user = userRepositroy.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }
    // get fetching all the record from db

    public List<Expense> getExpenses() {
        String userId = (String) SecurityContextHolder
                .getContext().
                getAuthentication().
                getPrincipal();

        Long id = Long.parseLong(userId);

        return expenseRepository.findByUserId(id);
    }
// SELECT * from expenses where id = ?
    //get fetching single record from db

    public Optional<Expense> getExpenseById(Long expenseId) {
        String userId = (String) SecurityContextHolder
                .getContext().
                getAuthentication().
                getPrincipal();

        Long id = Long.parseLong(userId);
        return expenseRepository.findByIdAndUserId(expenseId, id);
    }

    // we are updating the expense using the id
    public Expense updateExpenseById(Expense expense) {

        if (expense.getId() == null) {
            throw new RuntimeException("id must not be null");
        }

        if (!expenseRepository.existsById(expense.getId())) {
            throw new RuntimeException("expense id not found");
        }

        if (expense.getAmount() <= 0) {
            throw new RuntimeException("amount must be greater than 0");
        }
        if (expense.getCategory() == null) {
            throw new RuntimeException("category must not be null");
        }
        if (!expense.getCategory().trim().equalsIgnoreCase("income") &&
                !expense.getCategory().trim().equalsIgnoreCase("expense")) {
            throw new RuntimeException("category must not be income or expense");
        }

        String userId = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Long id = Long.parseLong(userId);
        Long expenseId = expense.getId();
        Expense expenseExist = expenseRepository.findByIdAndUserId(expenseId, id).
                orElseThrow(() -> new RuntimeException("expense id not found"));
        // SELECT * FROM expense WHERE userId = ? AND expenseId = ?
        expenseExist.setAmount(expense.getAmount());
        expenseExist.setCategory(expense.getCategory());
        expenseExist.setDescription(expense.getDescription());
        return expenseRepository.save(expenseExist);
    }
 @Transactional
    public void deleteExpenseById(Long expenseId) {

        String userId = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Long id = Long.parseLong(userId);
        expenseRepository.deleteByIdAndUserId(expenseId, id).
                orElseThrow(() -> new RuntimeException("expense id not foud"));

    }
}