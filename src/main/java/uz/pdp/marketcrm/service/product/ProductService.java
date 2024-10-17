package uz.pdp.marketcrm.service.product;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    void save(ProductRequest productRequest);
    void saleProducts(List<UUID> productIds, List<Integer> quantities);
    ProductEntity findById(UUID id);
    void update(ProductRequest productRequest);
    void deleteById(UUID id);
}
