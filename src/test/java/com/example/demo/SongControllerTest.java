package com.example.demo;

import com.example.demo.controllers.SongController;
import com.example.demo.models.Song;
import com.example.demo.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongRepository songRepository;

    @Test
    public void testGetSongs() throws Exception {
        Song song1 = new Song(1, "Song 1");
        Song song2 = new Song(2, "Song 2");
        Mockito.when(songRepository.findAll()).thenReturn(Arrays.asList(song1, song2));

        mockMvc.perform(get("/song"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("songs"))
                .andExpect(view().name("song/index"));
    }

    @Test
    public void testUpdateSong() throws Exception {
        Song song = new Song(1, "Updated Song");
        Mockito.when(songRepository.findById(1)).thenReturn(Optional.of(song));

        mockMvc.perform(get("/song/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("song"))
                .andExpect(view().name("song/update"));
    }

    @Test
    public void testDeleteSong() throws Exception {
        Mockito.doNothing().when(songRepository).deleteById(1);

        mockMvc.perform(post("/song/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/song"));
    }
}
