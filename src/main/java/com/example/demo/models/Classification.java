package com.example.demo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="classification")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classification_id;

    @Column(name="genre", length = 100)
    private String genre;

    @Column(name="release_date")
    private Date release_date;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="song_id", nullable = false)
    private Song song;







}
