package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "products")
public class ProductEntity extends BaseEntity{
    private String name;
    private String description;
    private double price;
    private Integer quantity;
}
