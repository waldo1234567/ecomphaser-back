package com.example.ecomphaser.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class CropsDTO {
    private UUID id;
    private String cropType;
    private Instant plantedTime;
    private int growthDuration;
    private boolean ready;
    private int growthPercentage;
    private float price;

    public CropsDTO(UUID id, String cropType, Instant plantedTime, int growthDuration, boolean ready , int growthPercentage, float price){
        this.id = id;
        this.cropType = cropType;
        this.plantedTime = plantedTime;
        this.growthDuration = growthDuration;
        this.ready = ready;
        this.growthPercentage = growthPercentage;
        this.price = price;
    }
}
