package co.neoris.service_bank.user_repository.transaction.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportDAO {
    private LocalDate createdAt;
    @Column("complete_name")
    private String fullNameClient;
    private Long accountNumber;
    private String accountType;
    @Column("initial_value")
    private BigDecimal accountInitialValue;
    @Column("state")
    private Boolean accountState;
    @Column("value")
    private BigDecimal transactionValue;
    @Column("balance")
    private BigDecimal availableBalance;
}
