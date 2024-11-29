package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "boxes")
@Builder
public class ProductBoxEntity extends BaseEntity {
    private UUID productId;
    private Integer amount;

}
