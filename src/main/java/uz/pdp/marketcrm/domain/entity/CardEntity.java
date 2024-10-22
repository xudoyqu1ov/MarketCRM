package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cards")
@Builder
public class CardEntity extends BaseEntity {
    private UUID productId;
    private Integer quantity;
}
