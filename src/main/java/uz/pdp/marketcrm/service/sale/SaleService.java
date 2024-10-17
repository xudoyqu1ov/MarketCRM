package uz.pdp.marketcrm.service.sale;

import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.ProductEntity;

import java.util.List;

@Service
public interface SaleService {
    void sale(List<ProductEntity> products);
}
