package uz.pdp.marketcrm.service.sale;

import lombok.RequiredArgsConstructor;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.SaleEntity;
import uz.pdp.marketcrm.repository.SaleRepository;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.List;
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final SaleRepository saleRepository;

    @Override
    public void sale(List<ProductEntity> products) {
        double sum = 0;
        for (ProductEntity product : products) {
            sum += product.getPrice() * product.getQuantity();
        }
        saleRepository.save(new SaleEntity(products,sum));
    }

    @Override
    public List<SaleEntity> getSales() {
        return saleRepository.findAll();
    }

}
