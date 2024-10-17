package uz.pdp.marketcrm.service.product;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.exception.BaseException;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;
import uz.pdp.marketcrm.repository.ProductRepository;
import uz.pdp.marketcrm.service.store.StoreService;

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
    public void saleProducts(List<UUID> productIds, List<Integer> quantityList) {
        for (int i = 0; i < productIds.size(); i++) {
            storeService.saleProduct(productIds.get(i), quantityList.get(i));
        }
    }

    @Override
    public ProductEntity findById(UUID id) {
        return null;
    }

    @Override
    public void update(ProductRequest productRequest) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
