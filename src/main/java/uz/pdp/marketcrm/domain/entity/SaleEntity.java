package uz.pdp.marketcrm.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "sales")
public class SaleEntity extends BaseEntity{
    @OneToMany
    private List<CardEntity> cardEntities;
    private double totalPrice;
}
