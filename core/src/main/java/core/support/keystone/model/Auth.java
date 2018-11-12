package core.support.keystone.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("auth")
public class Auth {
  private String tenantId;
  private PasswordCredentials passwordCredentials;

  @Data
  public static class PasswordCredentials {
    private String username;
    private String password;
  }

  public static Auth getInstance(String tenantId, String username, String password) {
    Auth auth = new Auth();
    auth.setTenantId(tenantId);
    auth.setPasswordCredentials(new Auth.PasswordCredentials());
    auth.getPasswordCredentials().setUsername(username);
    auth.getPasswordCredentials().setPassword(password);

    return auth;
  }
}
