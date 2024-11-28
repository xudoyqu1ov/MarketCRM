package uz.pdp.marketcrm.service.box;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductBoxEntity;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductBoxService {
    void save(ProductBoxEntity productBox);
    ProductBoxEntity findById(UUID id);
    List<ProductBoxEntity> findAll();
    void delete(UUID id);
    ProductBoxEntity findByProductId(UUID productId);

}
