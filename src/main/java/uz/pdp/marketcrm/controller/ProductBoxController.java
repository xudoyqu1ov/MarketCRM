package uz.pdp.marketcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.marketcrm.domain.entity.ProductBoxEntity;
import uz.pdp.marketcrm.service.box.ProductBoxService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/box")
@RequiredArgsConstructor
public class ProductBoxController {

    private final ProductBoxService productBoxService;

    @PostMapping("/create")
    public ResponseEntity<ProductBoxEntity> save(@RequestBody ProductBoxEntity productBox) {
        productBoxService.save(productBox);
        return ResponseEntity.ok(productBox);
    }

    @GetMapping("/find/by/{id}")
    public ResponseEntity<ProductBoxEntity> findById(@PathVariable UUID id) {
        ProductBoxEntity productBox = productBoxService.findById(id);
        return ResponseEntity.ok(productBox);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductBoxEntity>> findAll() {
        List<ProductBoxEntity> productBoxes = productBoxService.findAll();
        return ResponseEntity.ok(productBoxes);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productBoxService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/productId/{productId}")
    public ResponseEntity<ProductBoxEntity> findByProductId(@PathVariable UUID productId) {
        ProductBoxEntity productBox = productBoxService.findByProductId(productId);
        return ResponseEntity.ok(productBox);
    }
}
