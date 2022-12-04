package co.neoris.service_bank.user_repository.account;

import co.neoris.service_bank.model.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("account_banking")
public class AccountDAO {
    @Id
    private Long id;
    @Column("account_number")
    private Long number;
    @Column("account_type")
    private Account.AccountType type;
    private BigDecimal balance;
    private BigDecimal initialValue;
    private Boolean state;
    private Long personId;
}
