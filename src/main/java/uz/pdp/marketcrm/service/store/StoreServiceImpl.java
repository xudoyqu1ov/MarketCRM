package uz.pdp.marketcrm.service.store;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.repository.StoreRepository;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.List;
import java.util.UUID;



@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final ProductService productService;
    private final StoreRepository storeRepository;





    @Override
    public void saleProduct(CardEntity cardEntity) {
        StoreEntity allByProductId = storeRepository.findAllByProductId(cardEntity.getProductId());
        allByProductId.setAmount(allByProductId.getAmount() - cardEntity.getQuantity());
    }

    @Override
    public StoreEntity findStoreById(UUID id) {
        if (storeRepository.existsById(id)) {
            throw new BaseException("product already exists");
        }
        return storeRepository.findById(id).get();
    }

    @Override
    public void saveStore(StoreEntity storeEntity) {
        storeRepository.save(storeEntity);
    }

    @Override
    public List<StoreEntity> findAllStores() {
        return storeRepository.findAll();
    }


}
