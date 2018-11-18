package admin.web.ws.model;

import lombok.Data;

@Data
public class ChatMessage {
  private Type type;
  private String content;
  private String sender;

  public enum Type {
    JOIN, LEAVE, CHAT
  }
}
