package core.support.service;

public class Service2Client extends BaseClient implements ClientInterface {

  public Service2Client(String commonStringBean) {
    super(commonStringBean);
  }

  @Override
  public String getName() {
    return "Service2 (" + this.commonStringBean + ")";
  }
}
