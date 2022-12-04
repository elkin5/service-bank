package co.neoris.service_bank.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private String identification;
    @JsonProperty("account_number")
    private String number;
    private String type;
    @JsonProperty("initial_value")
    private String initialValue;
}
