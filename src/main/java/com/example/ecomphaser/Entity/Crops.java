package com.example.ecomphaser.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crops {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("cropType")
    private String cropType;

    @Column(nullable = false)
    @JsonProperty("plantedTime")
    private Instant plantedTime;

    @Column(nullable = false)
    @JsonProperty("growthDuration")
    private int growthDuration;

    @Column(nullable = false)
    @JsonProperty("harvested")
    private boolean harvested = false;

    @ManyToOne
    @JoinColumn(name ="player_id", nullable = true)
    @JsonProperty("harvestedBy")
    @JsonBackReference
    private Player harvestedBy;

    @Column(nullable = true)
    @JsonProperty("harvestedTime")
    private Instant harvestedTime;

    @Column(nullable = false)
    @JsonProperty("price")
    private float price;

}
