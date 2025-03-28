package com.example.ecomphaser.Controller;

import com.example.ecomphaser.Entity.Inventory;
import com.example.ecomphaser.Exception.ResourceNotFoundException;
import com.example.ecomphaser.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/inventory")
@CrossOrigin(origins = "http://localhost:5173/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/decrease-item")
    public ResponseEntity<?> decreaseItem(
            @RequestParam("player_id") UUID player_id,
            @RequestParam("itemName") String itemName,
            @RequestParam("amount") int amount
    ){
        try{
            Inventory updatedInventory = inventoryService.decreaseItem(player_id, itemName , amount);
            if(updatedInventory == null){
                return ResponseEntity.ok("Item removed from inventory");
            }
            return ResponseEntity.ok(updatedInventory);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping(path = "/add-item")
    public ResponseEntity<?> addItem(
            @RequestParam("player_id") UUID player_id,
            @RequestParam("itemName") String itemName,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity
    ){
        try{
            Inventory inventory = inventoryService.saveOrUpdateInventory(player_id, itemName, quantity);
            return ResponseEntity.ok(inventory);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
