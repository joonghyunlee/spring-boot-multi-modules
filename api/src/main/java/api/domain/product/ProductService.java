package api.domain.product;

import api.domain.product.model.Product;
import core.support.service.Service1Client;
import core.support.service.Service2Client;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private Service1Client service1Client;
  private Service2Client service2Client;

  public List<Product> listProduct() {
    List<Product> productList = new ArrayList<>();
    productList.add(new Product()
        .setId(UUID.randomUUID().toString())
        .setName(service1Client.getName()));

    productList.add(new Product()
        .setId(UUID.randomUUID().toString())
        .setName(service2Client.getName()));

    return productList;
  }
}
