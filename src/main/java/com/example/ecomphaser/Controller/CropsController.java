package com.example.ecomphaser.Controller;

import com.example.ecomphaser.DTO.CropsDTO;
import com.example.ecomphaser.Service.CropsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/crops")
@CrossOrigin(origins = "http://localhost:5173/")
public class CropsController {
    @Autowired
    private CropsService cropsService;

    @GetMapping
    public ResponseEntity<List<CropsDTO>> getAvailableCrops(){
        return ResponseEntity.ok(cropsService.findAvailableHarvest());
    }

    @GetMapping(path = "/by-type")
    public ResponseEntity<List<CropsDTO>> getByType(
            @RequestParam("type")
            String type
    ){
        return  ResponseEntity.ok(cropsService.findCropByType(type));
    }

    @GetMapping(path = "/by-id")
    public ResponseEntity<List<CropsDTO>> getPlayerInventory(
            @RequestParam("player_id")
            UUID player_id
    ){
        return ResponseEntity.ok(cropsService.findByHarvestAndId(player_id));
    }

    @PostMapping(path = "/plant")
    public ResponseEntity<CropsDTO> plantCrops(
            @RequestBody CropsDTO cropsDTO
    ){
        CropsDTO plantedCrop = cropsService.plantCrops(
            cropsDTO.getCropType(),
            cropsDTO.getGrowthDuration(),
            cropsDTO.getPrice()
        );

        return  ResponseEntity.ok(plantedCrop);
    }
}
