package api.domain.product.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
  private String id;
  private String name;
}
