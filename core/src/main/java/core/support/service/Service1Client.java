package core.support.service;

public class Service1Client extends BaseClient implements ClientInterface {

  @Override
  public String getName() {
    return "Service1";
  }
}
