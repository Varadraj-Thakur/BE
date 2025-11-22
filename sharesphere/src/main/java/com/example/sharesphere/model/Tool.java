package com.example.sharesphere.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "tools")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Long ownerId;         // reference to user (store id for now)

    @Column(nullable = false)
    private boolean available = true;

    private String location;      // city / address summary

    private Double pricePerHour;  // nullable


    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void onCreate() { createdAt = updatedAt = Instant.now(); }

    @PreUpdate
    public void onUpdate() { updatedAt = Instant.now(); }
}
