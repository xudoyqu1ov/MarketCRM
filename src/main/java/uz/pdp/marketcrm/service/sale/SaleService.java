package uz.pdp.marketcrm.service.sale;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.SaleEntity;

import java.util.List;

@Service
public interface SaleService {
    void sale(SaleEntity saleEntity);
    List<SaleEntity> getSales();
}