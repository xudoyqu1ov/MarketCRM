package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "stores")
public class StoreEntity extends BaseEntity{
    @ManyToOne
    private ProductEntity product;
    private Integer amount;
}
