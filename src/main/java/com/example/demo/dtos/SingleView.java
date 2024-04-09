package com.example.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.models.Artist;
import com.example.demo.models.Song;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleView {

    private Artist artist;
    private Song song;
    private String genre;
    private Date release_date;

}
