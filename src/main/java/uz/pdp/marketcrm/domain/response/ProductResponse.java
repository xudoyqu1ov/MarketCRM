package uz.pdp.marketcrm.domain.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {
    private UUID productId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
