package api.web.user;

import api.domain.user.UserService;
import api.domain.user.model.User;
import api.web.user.model.UserListResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public UserListResponse listUser() {
    List<User> userList = userService.listUser();

    return UserListResponse.getInstance(userList);
  }
}
