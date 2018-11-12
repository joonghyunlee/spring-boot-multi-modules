package core.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "keystone")
public class CoreKeystoneProperties {
  private String endpoint;
  private String tenantId;
  private String username;
  private String password;
}
