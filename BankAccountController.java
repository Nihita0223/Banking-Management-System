control layer
package com.bank.controller;

import com.bank.model.BankAccount;
import com.bank.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@Valid @RequestBody BankAccount account) {
        return ResponseEntity.ok(service.createAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<BankAccount> deposit(@PathVariable Long id, @RequestParam Double amount) {
        return ResponseEntity.ok(service.deposit(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<BankAccount> withdraw(@PathVariable Long id, @RequestParam Double amount) {
        return ResponseEntity.ok(service.withdraw(id, amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam Double amount) {
        service.transfer(fromId, toId, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}
