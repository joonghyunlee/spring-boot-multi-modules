package api.web.product.model;

import api.domain.product.model.Product;
import java.util.List;
import lombok.Data;

@Data
public class ProductListResponse {
  private List<Product> products;

  public static ProductListResponse getInstance(List<Product> products) {
    ProductListResponse response = new ProductListResponse();
    response.setProducts(products);

    return response;
  }
}
