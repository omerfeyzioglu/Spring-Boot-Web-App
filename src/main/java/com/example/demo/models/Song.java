package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title", length = 200)
    private String title;

    @Column(name = "duration_time")
    private LocalTime duration_time;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Classification> classifications;
}
