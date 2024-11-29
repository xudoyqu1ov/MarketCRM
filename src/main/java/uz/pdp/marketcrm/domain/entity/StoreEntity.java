package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "stores")
public class StoreEntity extends BaseEntity{
    @OneToMany
    private List<ProductBoxEntity> products;

}
