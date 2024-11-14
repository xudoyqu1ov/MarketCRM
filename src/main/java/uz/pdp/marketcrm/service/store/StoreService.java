package uz.pdp.marketcrm.service.store;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface StoreService {
    void saleProduct(CardEntity cardEntity);
    void saveStore(StoreEntity storeEntity);
    List<StoreEntity> findAllStores();
    StoreEntity findStoreById(UUID id);

}
