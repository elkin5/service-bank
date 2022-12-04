package co.neoris.service_bank.user_repository.user_banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class UserBankingDAO {
    private Long id;
    private String userPassword;
    private Boolean state;
    private Long personId;
}
