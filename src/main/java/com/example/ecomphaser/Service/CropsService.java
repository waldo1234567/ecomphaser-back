package com.example.ecomphaser.Service;

import com.example.ecomphaser.DTO.CropsDTO;
import com.example.ecomphaser.Entity.Crops;
import com.example.ecomphaser.Repository.CropsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CropsService {
    @Autowired
    private CropsRepository cropsRepository;

    public List<CropsDTO> findByHarvestAndId(UUID player_id){
        List<Crops> crops = cropsRepository.findByHarvestedBy_Id(player_id);
        return crops.stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public List<CropsDTO> findAvailableHarvest(){
        List<Crops> available = cropsRepository.findByHarvestedFalse();
        return available.stream().map(this :: convertDTO).collect(Collectors.toList());
    }

    public List<CropsDTO> findCropByType(String cropType){
        List<Crops> crops = cropsRepository.findByCropType(cropType);
        return crops.stream().map(this::convertDTO).collect(Collectors.toList());
    }
    public void deleteCrops(UUID id){
        cropsRepository.deleteById(id);
    }

    public CropsDTO plantCrops(String cropType , int growthDuration, float price ){
        Crops newCrops = Crops.builder()
                .cropType(cropType)
                .plantedTime(Instant.now())
                .growthDuration(growthDuration)
                .price(price)
                .harvested(false)
                .build();
        cropsRepository.save(newCrops);

        return convertDTO(newCrops);
    }

    private CropsDTO convertDTO(Crops crops){
        Instant now = Instant.now();
        long elapsedTime = Duration.between(crops.getPlantedTime(), now).toMinutes();
        int growthPercentage = (int) Math.min((elapsedTime * 100) / crops.getGrowthDuration(), 100);
        boolean ready = growthPercentage >= 100;

        return new CropsDTO(
                crops.getId(),
                crops.getCropType(),
                crops.getPlantedTime(),
                crops.getGrowthDuration(),
                ready,
                growthPercentage,
                crops.getPrice()
        );
    }
}
