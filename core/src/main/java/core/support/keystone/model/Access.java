package core.support.keystone.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("access")
public class Access {
  private Token token;

  @Data
  public static class Token {
    private String expires;
    private String id;
    private Tenant tenant;

    @Data
    public static class Tenant {
      private String id;
      private String name;
      private Boolean enabled;
    }
  }
}
