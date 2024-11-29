package uz.pdp.marketcrm.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductBoxEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.repository.StoreRepository;
import uz.pdp.marketcrm.service.box.ProductBoxService;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final ProductBoxService productBoxService;
    @Override
    public void saleProduct(CardEntity cardEntity) {
        ProductBoxEntity productBox = productBoxService.findByProductId(cardEntity.getProductId());
        productBox.setAmount(productBox.getAmount() - cardEntity.getQuantity());
    }

    @Override
    public void saveStore(StoreEntity storeEntity) {
        storeRepository.save(storeEntity);
    }

    @Override
    public List<StoreEntity> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public StoreEntity findStoreById(UUID id) {
        return storeRepository.findById(id).orElseThrow(()-> new BaseException("Store not found"));
    }


}
