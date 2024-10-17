package uz.pdp.marketcrm.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}