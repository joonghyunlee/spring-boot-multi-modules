package api.web.user.model;

import api.domain.user.model.User;
import java.util.List;
import lombok.Data;

@Data
public class UserListResponse {
  private List<User> users;

  public static UserListResponse getInstance(List<User> users) {
    UserListResponse response = new UserListResponse();
    response.setUsers(users);

    return response;
  }
}
