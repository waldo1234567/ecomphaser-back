package com.example.ecomphaser.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty("id")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    @JsonProperty("playerId")
    @JsonBackReference
    private Player player;

    @Column(name = "item_name", nullable = false)
    @JsonProperty("item_name")
    private String itemName;

    @Column(nullable = false)
    @JsonProperty("quantity")
    private int quantity;

}
