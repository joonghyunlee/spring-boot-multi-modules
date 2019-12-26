package core.support.service;

public class Service1Client extends BaseClient implements ClientInterface {

  public Service1Client(String commonStringBean) {
    super(commonStringBean);
  }

  @Override
  public String getName() {
    return "Service1 (" + this.commonStringBean + ")";
  }
}
