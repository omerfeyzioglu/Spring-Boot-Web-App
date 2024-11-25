package com.example.demo;

import com.example.demo.controllers.ArtistController;
import com.example.demo.models.Artist;
import com.example.demo.repositories.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository artistRepository;

    @Test
    public void testGetArtists() throws Exception {
        Artist artist1 = new Artist(1, "Artist 1");
        Artist artist2 = new Artist(2, "Artist 2");
        Mockito.when(artistRepository.findAll()).thenReturn(Arrays.asList(artist1, artist2));

        mockMvc.perform(get("/artist"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("artist"))
                .andExpect(view().name("artist/index"));
    }

    @Test
    public void testAddArtist() throws Exception {
        Artist newArtist = new Artist("New Artist");
        Mockito.when(artistRepository.save(Mockito.any(Artist.class))).thenReturn(newArtist);

        mockMvc.perform(post("/car/add")
                        .param("name", "New Artist"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/artist"));
    }
}
