package uz.pdp.marketcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.marketcrm.domain.entity.CardEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;
import uz.pdp.marketcrm.service.store.StoreService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @PostMapping("/create")
    public ResponseEntity<String> saveStore(@RequestBody StoreEntity storeEntity) {
        storeService.saveStore(storeEntity);
        return ResponseEntity.ok("Store saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreEntity>> findAllStores() {
        List<StoreEntity> stores = storeService.findAllStores();
        return ResponseEntity.ok(stores);
    }
    @GetMapping("/find/by/{id}")
    public ResponseEntity<StoreEntity> findStoreById(@PathVariable UUID id) {
        storeService.findStoreById(id);
        return ResponseEntity.ok(storeService.findStoreById(id));
    }
}
