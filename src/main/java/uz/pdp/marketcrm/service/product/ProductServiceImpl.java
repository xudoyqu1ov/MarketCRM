package uz.pdp.marketcrm.service.product;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.exception.BaseException;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;
import uz.pdp.marketcrm.repository.ProductRepository;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;
    @Override
    public void save(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new BaseException("product already exist");
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> saleProducts(List<UUID> productIds, List<Integer> quantityList) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            storeService.saleProduct(productIds.get(i), quantityList.get(i));
            ProductEntity byId = findById(productIds.get(i));
            byId.setQuantity(quantityList.get(i));
            productEntities.add(byId);
        }
        return productEntities;
    }

    @Override
    public ProductEntity findById(UUID id) {
        if (productRepository.existsById(id)) {
            throw new BaseException("product already exist");
        }
        return productRepository.findById(id).orElseThrow(() -> new BaseException("product not found"));
    }

    @Override
    public void update(UUID id, ProductRequest productRequest) {
        ProductEntity byId = findById(id);
        byId.setName(productRequest.getName());
        byId.setDescription(productRequest.getDescription());
        byId.setPrice(productRequest.getPrice());
    }

    @Override
    public void deleteById(UUID id) {
        findById(id);
        productRepository.deleteById(id);
    }
}
