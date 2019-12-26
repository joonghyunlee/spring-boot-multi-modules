package core.support.keystone;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import core.common.config.CoreKeystoneProperties;
import core.common.config.HttpClientConfiguration;
import core.support.keystone.model.Access;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {HttpClientConfiguration.class, CoreKeystoneProperties.class, KeystoneRepository.class})
@ActiveProfiles("test")
public class KeystoneRepositoryTest {
  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private KeystoneRepository keystoneRepository;

  @Test
  public void createTokenSuccessfulTest() {
    // given

    // when
    Access access = keystoneRepository.createToken();

    // then
    assertNotNull(access);
    LOG.debug(access.toString());
  }
}
