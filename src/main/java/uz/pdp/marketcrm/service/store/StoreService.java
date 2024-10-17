package uz.pdp.marketcrm.service.store;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface StoreService {
    void saleProduct(UUID productId, Integer quantity);

}
