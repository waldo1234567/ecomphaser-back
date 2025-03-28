package com.example.ecomphaser.Repository;

import com.example.ecomphaser.Entity.Inventory;
import com.example.ecomphaser.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory , UUID> {
    Optional<Inventory> findByPlayer_Id(UUID player_id);
    Optional<Inventory> findByPlayerAndItemName(Player player, String itemName);
}
