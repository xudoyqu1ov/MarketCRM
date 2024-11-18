package uz.pdp.marketcrm.service.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.SaleEntity;
import uz.pdp.marketcrm.repository.SaleRepository;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Override
    public void sale(SaleEntity saleEntity) {
        saleRepository.save(saleEntity);
    }

    @Override
    public List<SaleEntity> getSales() {
        return saleRepository.findAll();
    }

}
