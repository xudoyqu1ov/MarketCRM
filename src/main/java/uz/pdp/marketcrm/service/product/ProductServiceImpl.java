package uz.pdp.marketcrm.service.product;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;

import uz.pdp.marketcrm.domain.entity.SaleEntity;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.repository.ProductRepository;
import uz.pdp.marketcrm.service.sale.SaleService;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final SaleService saleService;

    @Override
    public void save(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new BaseException("product already exist");
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> saleProducts(List<CardEntity> cardEntities) {
        List<ProductEntity> productEntities = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < cardEntities.size(); i++) {
            ProductEntity byId = findById(cardEntities.get(i).getProductId());
            productEntities.add(byId);
            sum += byId.getPrice() * cardEntities.get(i).getQuantity();
            storeService.saleProduct(cardEntities.get(i));
        }
        saleService.sale(new SaleEntity(cardEntities, sum));
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

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
