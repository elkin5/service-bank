package co.neoris.service_bank.user_repository.transaction;

import co.neoris.service_bank.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("transaction_banking")
public class TransactionDAO {
    @Id
    private Long id;
    private BigDecimal value;
    private BigDecimal initialValue;
    private BigDecimal endingValue;
    @Column("transaction_type")
    private Transaction.TransactionType type;
    private LocalDateTime createdAt;
    private Long accountId;
}
