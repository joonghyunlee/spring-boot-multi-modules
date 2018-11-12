package core.support.keystone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import core.common.config.CoreKeystoneProperties;
import core.support.keystone.model.Access;
import core.support.keystone.model.Auth;

@Repository
public class KeystoneRepository {
  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  private static final String TOKEN_URI = "/tokens";

  @Autowired
  private CoreKeystoneProperties keystoneProperties;

  @Autowired
  private RestTemplate restTemplate;

  public Access createToken() {
    String url = keystoneProperties.getEndpoint() + TOKEN_URI;
    LOG.debug("url: " + url);
    Auth auth = Auth.getInstance(keystoneProperties.getTenantId(), keystoneProperties.getUsername(),
        keystoneProperties.getPassword());

    return restTemplate.postForObject(url, auth, Access.class);
  }
}
