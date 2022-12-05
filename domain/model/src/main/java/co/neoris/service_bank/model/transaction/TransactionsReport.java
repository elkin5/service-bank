package co.neoris.service_bank.model.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TransactionsReport {
    private LocalDate createdAt;
    private String fullNameClient;
    private Long accountNumber;
    private String accountType;
    private BigDecimal accountInitialValue;
    private Boolean accountState;
    private BigDecimal transactionValue;
    private BigDecimal availableBalance;
}
