package com.example.demo.controllers;

import com.example.demo.models.Artist;
import com.example.demo.models.Classification;
import com.example.demo.models.Song;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.ClassificationRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.dtos.SingleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SingleController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ClassificationRepository classificationRepository;

    @GetMapping("/single")
    public String showSingleForm(Model model) {
        List<Artist> artists = artistRepository.findAll();
        List<Song> songs = songRepository.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("songs", songs);
        model.addAttribute("singleView", new SingleView());
        return "single/index";
    }

    @PostMapping("/single/add")
    public String createSingle(@ModelAttribute("singleView") SingleView singleView) {
        Artist artist = singleView.getArtist();
        Song song = singleView.getSong();

        artist = artistRepository.findById(artist.getId()).orElse(null);
        song = songRepository.findById(song.getId()).orElse(null);

        Classification classification = new Classification();
        classificationRepository.save(classification);
        artist.getClassifications().add(classification);
        song.getClassifications().add(classification);
        artistRepository.save(artist);
        songRepository.save(song);

        return "redirect:/single";
    }

    @GetMapping("/single/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Classification classification = classificationRepository.findById(id).orElse(null);
        model.addAttribute("classification", classification);
        return "single/update";
    }

    @PostMapping("/single/update/{id}")
    public String updateSingle(@PathVariable("id") int id, @ModelAttribute("classification") Classification classification) {
        classificationRepository.save(classification);
        return "redirect:/single";
    }

    @PostMapping("/single/delete/{id}")
    public String deleteSingle(@PathVariable("id") int id) {
        classificationRepository.deleteById(id);
        return "redirect:/single";
    }
}
