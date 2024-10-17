package uz.pdp.marketcrm.service.store;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.repository.StoreRepository;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.UUID;
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final ProductService productService;
    private final StoreRepository storeRepository;

    @Override
    public void saleProduct(UUID productId, Integer quantity) {
        ProductEntity product = getProduct(productId);
        product.setQuantity(product.getQuantity() - quantity);
    }

    @Override
    public ProductEntity getProduct(UUID productId) {
        if (productId == null) {
            throw new BaseException("productId is null");
        }
        return storeRepository.findAllByProductId(productId)
                .orElseThrow(() -> new BaseException("product not found"));
    }
}
