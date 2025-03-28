package com.example.ecomphaser.Config;

import com.example.ecomphaser.Entity.Crops;
import com.example.ecomphaser.Entity.Player;
import com.example.ecomphaser.Repository.CropsRepository;
import com.example.ecomphaser.Repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class DataInjection {
    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository, CropsRepository cropsRepository){
        return args -> {
            if(playerRepository.count() > 0){
                return;
            }

            Player player1 =  Player.builder()
                    .username("john_doe")
                    .email("john@example.com")
                    .build();
            playerRepository.save(player1);

            Player player2 = Player.builder()
                    .username("jane_doe")
                    .email("jane@example.com")
                    .build();
            playerRepository.save(player2);

            Crops crop1 = Crops.builder()
                    .cropType("wheat")
                    .plantedTime(Instant.now().minusSeconds(30)) // planted 20 minutes ago
                    .growthDuration(1) // 30 minutes to fully grow
                    .harvested(false)
                    .price(10.0f)
                    .build();
            cropsRepository.save(crop1);

            Crops crop2 = Crops.builder()
                    .cropType("wheat")
                    .plantedTime(Instant.now().minusSeconds(60)) // planted 10 minutes ago
                    .growthDuration(2)
                    .harvested(false)
                    .price(15.0f)
                    .build();
            cropsRepository.save(crop2);

            Crops crop3 = Crops.builder()
                    .cropType("wheat")
                    .plantedTime(Instant.now().minusSeconds(20)) // planted 30 minutes ago
                    .growthDuration(1)
                    .harvested(true) // harvested crop, for inventory
                    .harvestedTime(Instant.now().minusSeconds(300)) // harvested 5 minutes ago
                    .harvestedBy(player1)
                    .price(10.0f)
                    .build();
            cropsRepository.save(crop3);

            Crops crop4 = Crops.builder()
                    .cropType("tomato")
                    .plantedTime(Instant.now().minusSeconds(30)) // planted 20 minutes ago
                    .growthDuration(1) // 30 minutes to fully grow
                    .harvested(false)
                    .price(10.0f)
                    .build();
            cropsRepository.save(crop4);
        };
    }

}
