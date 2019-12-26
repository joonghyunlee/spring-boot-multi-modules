package api.web.product;

import api.domain.product.ProductService;
import api.domain.product.model.Product;
import api.web.product.model.ProductListResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

  private ProductService productService;

  @RequestMapping(value = "/products", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public ProductListResponse listProduct() {
    List<Product> productList = productService.listProduct();

    return ProductListResponse.getInstance(productList);
  }
}
