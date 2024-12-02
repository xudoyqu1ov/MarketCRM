package uz.pdp.marketcrm.service.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.SaleEntity;
import uz.pdp.marketcrm.repository.SaleRepository;
import uz.pdp.marketcrm.service.product.ProductService;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final StoreService storeService;

    @Override
    public void save(SaleEntity saleEntity) {
        saleRepository.save(saleEntity);
    }

    @Override
    public List<SaleEntity> getSales() {
        return saleRepository.findAll();
    }
    @Override
    public List<ProductEntity> saleProduct(List<CardEntity> cardEntities) {
        List<ProductEntity> productEntities = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < cardEntities.size(); i++) {
            ProductEntity byId = productService.findById(cardEntities.get(i).getProductId());
            productEntities.add(byId);
            sum += byId.getPrice() * cardEntities.get(i).getQuantity();
            storeService.saleProduct(cardEntities.get(i));
        }
        save(new SaleEntity(cardEntities, sum));
        return productEntities;
    }


}
