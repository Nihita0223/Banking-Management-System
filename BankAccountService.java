package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(BankAccount account) {
        return repository.save(account);
    }

    public BankAccount getAccountById(Long id) {
        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
    }

    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }

    @Transactional
    public BankAccount deposit(Long id, Double amount) {
        BankAccount account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    @Transactional
    public BankAccount withdraw(Long id, Double amount) {
        BankAccount account = getAccountById(id);
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }
}

service layer
