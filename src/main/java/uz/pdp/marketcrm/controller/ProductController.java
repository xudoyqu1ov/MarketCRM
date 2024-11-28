package uz.pdp.marketcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.enumurators.ProductCategory;
import uz.pdp.marketcrm.domain.request.ProductRequest;
import uz.pdp.marketcrm.service.product.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("/find/by/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable UUID id) {
        ProductEntity product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/find/{category}")
    public ResponseEntity<List<ProductEntity>> getProductsByCategory(@PathVariable ProductCategory category) {
        List<ProductEntity> byProductCategory = productService.findByProductCategory(category);
        return ResponseEntity.ok(byProductCategory);
    }


}
