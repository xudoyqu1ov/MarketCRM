package uz.pdp.marketcrm.service.store;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductEntity;

import java.util.UUID;

@Service
public interface StoreService {
    void saleProduct(UUID productId, Integer quantity);
    ProductEntity getProduct(UUID productId);

}
