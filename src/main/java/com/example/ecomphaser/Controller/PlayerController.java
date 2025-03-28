package com.example.ecomphaser.Controller;

import com.example.ecomphaser.Entity.Player;
import com.example.ecomphaser.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(path = "api/player")
@CrossOrigin(origins = "http://localhost:5173/")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(path = "/by-id")
    public ResponseEntity<Player> getById(
            @RequestParam("id")
            UUID id
    ){
        return playerService.findPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Player> makePlayer(
            @RequestBody Player player
    ){
        Player newplayer = playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(newplayer);
    }


}
