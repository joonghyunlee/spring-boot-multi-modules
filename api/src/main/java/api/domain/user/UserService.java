package api.domain.user;

import api.domain.user.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public List<User> listUser() {
    List<User> userList = new ArrayList<>();

    userList.add(new User()
        .setId(UUID.randomUUID().toString())
        .setName(RandomStringUtils.randomAlphabetic(10)));

    userList.add(new User()
        .setId(UUID.randomUUID().toString())
        .setName(RandomStringUtils.randomAlphabetic(10)));

    return userList;
  }
}
