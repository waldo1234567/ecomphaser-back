package com.example.ecomphaser.Repository;

import com.example.ecomphaser.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player , UUID> {


    Optional<Player> findPlayerByUsername(String username);
}
