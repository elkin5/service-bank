package co.neoris.service_bank.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.r2bdc-postgres")
public class PostgresSQLConnectionProperties {
    private String database;
    private String schema;
    private String username;
    private String password;
    private String host;
    private Integer port;
}
