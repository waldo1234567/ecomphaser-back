package com.example.ecomphaser.Service;

import com.example.ecomphaser.Entity.Player;
import com.example.ecomphaser.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Player> findPlayerById(UUID playerId){
        return playerRepository.findById(playerId);
    }

    public Player createPlayer(Player player){
        return playerRepository.save(player);
    }

    public Optional<Player> findByUsername(String username){
        return playerRepository.findPlayerByUsername(username);
    }
}
