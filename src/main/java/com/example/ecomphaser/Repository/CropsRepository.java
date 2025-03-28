package com.example.ecomphaser.Repository;

import com.example.ecomphaser.Entity.Crops;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CropsRepository extends JpaRepository<Crops, UUID> {
    List<Crops> findByHarvestedFalse();

    List<Crops> findByCropType(String cropType);

    List<Crops> findByHarvestedBy_Id(UUID player_id);
}
