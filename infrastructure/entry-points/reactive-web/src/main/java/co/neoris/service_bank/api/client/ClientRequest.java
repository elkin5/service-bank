package co.neoris.service_bank.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @JsonProperty("complete_name")
    private String completeName;
    private String gender;
    @JsonProperty("birth_date")
    private String birthDate;
    private String identification;
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String password;
}
