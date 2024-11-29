package uz.pdp.marketcrm.service.box;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductBoxEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.repository.ProductBoxRepository;
import uz.pdp.marketcrm.repository.ProductRepository;
import uz.pdp.marketcrm.repository.StoreRepository;
import uz.pdp.marketcrm.service.product.ProductService;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductBoxServiceImpl implements ProductBoxService {

    private final ProductBoxRepository productBoxRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;


    @Override
    public void save(ProductBoxEntity productBox) {
        ProductBoxEntity save = productBoxRepository.save(productBox);
        storeRepository.save(new StoreEntity(List.of(save)));
    }
    @Override
    public ProductBoxEntity findById(UUID id) {
        return productBoxRepository.findById(id).orElseThrow(() -> new BaseException("product not found"));
    }

    @Override
    public List<ProductBoxEntity> findAll() {
        return productBoxRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        ProductBoxEntity byId = findById(id);
        productRepository.deleteById(byId.getProductId());
        productBoxRepository.deleteById(id);
    }

    @Override
    public ProductBoxEntity findByProductId(UUID productId) {
        return productBoxRepository.findByProductId(productId).orElseThrow(() -> new BaseException("product not found"));
    }
}
