entity class 
package com.bank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;

    @NotNull(message = "Balance cannot be null")
    @Min(value = 100, message = "Minimum balance must be 100")
    private Double balance;
}