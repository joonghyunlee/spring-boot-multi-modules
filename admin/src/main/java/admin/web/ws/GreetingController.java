package admin.web.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import admin.web.ws.model.Greeting;
import admin.web.ws.model.HelloMessage;

@Controller
public class GreetingController {
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) {
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()));
  }
}
