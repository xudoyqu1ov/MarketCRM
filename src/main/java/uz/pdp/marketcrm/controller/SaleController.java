package uz.pdp.marketcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.marketcrm.domain.entity.SaleEntity;
import uz.pdp.marketcrm.service.sale.SaleService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleEntity>> getAllSales() {
        List<SaleEntity> sales = saleService.getSales();
        return ResponseEntity.ok(sales);
    }
}