package core.support.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseClient {
  @NonNull
  String commonStringBean;

  public String init() {
    return "Initialize";
  }
}
