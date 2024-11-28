package uz.pdp.marketcrm.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.*;

import uz.pdp.marketcrm.domain.enumurators.ProductCategory;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.domain.response.ProductResponse;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.repository.ProductBoxRepository;
import uz.pdp.marketcrm.repository.ProductRepository;
import uz.pdp.marketcrm.service.box.ProductBoxService;
import uz.pdp.marketcrm.service.sale.SaleService;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductBoxService productBoxService;

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
        ProductEntity product = productRepository.save(productEntity);
        productBoxService.save(new ProductBoxEntity(product.getId(), productRequest.getQuantity()));
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

    @Override
    public ProductEntity findByProductName(String productName) {
        return productRepository.findByName(productName).orElseThrow(() -> new BaseException("product not found"));
    }

    @Override
    public List<ProductEntity> findByProductCategory(ProductCategory productCategory) {
        return productRepository.findAllByCategory(productCategory);
    }
}
