Unit Test for BankAccountService (BankAccountServiceTest.java)

package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountServiceTest {

    @Mock
    private BankAccountRepository repository;

    @InjectMocks
    private BankAccountService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        BankAccount account = new BankAccount();
        account.setAccountHolderName("John Doe");
        account.setBalance(500.0);

        when(repository.save(any(BankAccount.class))).thenReturn(account);

        BankAccount savedAccount = service.createAccount(account);

        assertNotNull(savedAccount);
        assertEquals("John Doe", savedAccount.getAccountHolderName());
    }

    @Test
    void testGetAccountById_ValidId() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setAccountHolderName("John Doe");
        account.setBalance(500.0);

        when(repository.findById(1L)).thenReturn(Optional.of(account));

        BankAccount fetchedAccount = service.getAccountById(1L);

        assertNotNull(fetchedAccount);
        assertEquals(1L, fetchedAccount.getId());
    }

    @Test
    void testGetAccountById_InvalidId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> service.getAccountById(1L));
    }

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setBalance(500.0);

        when(repository.findById(1L)).thenReturn(Optional.of(account));
        when(repository.save(any(BankAccount.class))).thenReturn(account);

        BankAccount updatedAccount = service.deposit(1L, 200.0);

        assertEquals(700.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdraw_SufficientBalance() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setBalance(500.0);

        when(repository.findById(1L)).thenReturn(Optional.of(account));
        when(repository.save(any(BankAccount.class))).thenReturn(account);

        BankAccount updatedAccount = service.withdraw(1L, 100.0);

        assertEquals(400.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdraw_InsufficientBalance() {
        BankAccount account = new BankAccount();
        account.setId(1L);
        account.setBalance(100.0);

        when(repository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(IllegalArgumentException.class, () -> service.withdraw(1L, 200.0));
    }
}
