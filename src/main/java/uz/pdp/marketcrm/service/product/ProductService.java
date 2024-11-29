package uz.pdp.marketcrm.service.product;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.enumurators.ProductCategory;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    void save(ProductRequest productRequest);
    ProductEntity findById(UUID id);
    void update(UUID id, ProductRequest productRequest);
    void deleteById(UUID id);
    List<ProductEntity> findAll();
    ProductEntity findByProductName(String productName);
    List<ProductEntity> findByProductCategory(ProductCategory productCategory);

}
