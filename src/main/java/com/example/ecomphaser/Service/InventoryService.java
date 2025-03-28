package com.example.ecomphaser.Service;

import com.example.ecomphaser.Entity.Inventory;
import com.example.ecomphaser.Entity.Player;
import com.example.ecomphaser.Exception.ResourceNotFoundException;
import com.example.ecomphaser.Repository.InventoryRepository;
import com.example.ecomphaser.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Inventory> findInvByPlayer(UUID player_id){
        return inventoryRepository.findByPlayer_Id(player_id);
    }

    public Inventory saveOrUpdateInventory(UUID player_id, String itemName, int quantity){
        Player player = playerRepository.findById(player_id).orElseThrow(() -> new ResourceNotFoundException("Player not found with id" + player_id));

        Optional<Inventory> optionalInventory = inventoryRepository.findByPlayerAndItemName(player, itemName);
        Inventory inventory;

        if(optionalInventory.isPresent()){
            inventory = optionalInventory.get();
            inventory.setQuantity(inventory.getQuantity() + 1);
        }else{
            inventory = Inventory.builder()
                    .player(player)
                    .itemName(itemName)
                    .quantity(quantity)
                    .build();
        }

        return inventoryRepository.save(inventory);
    }

    public Inventory decreaseItem(UUID player_id ,String itemName, int descQuantity){
        Player player = playerRepository.findById(player_id).orElseThrow(() -> new ResourceNotFoundException("Player not found with id" + player_id));

        Inventory getInventory = inventoryRepository.findByPlayerAndItemName(player , itemName).orElseThrow(() -> new ResourceNotFoundException("Inventory record not found" + itemName));

        int newQuantity = getInventory.getQuantity() - descQuantity;

        if(newQuantity <= 0 ){
            inventoryRepository.delete(getInventory);
            return null;
        }else{
            getInventory.setQuantity(newQuantity);
            return inventoryRepository.save(getInventory);
        }
    }
}
