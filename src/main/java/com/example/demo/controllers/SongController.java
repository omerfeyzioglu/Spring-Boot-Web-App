package com.example.demo.controllers;

import com.example.demo.models.Song;
import com.example.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SongController {

    @Autowired
    private SongRepository sRp;

    @GetMapping("/song")
    private String getSongs(Model model){
        Iterable<Song> songs = sRp.findAll();
        model.addAttribute("songs", songs);
        return "song/index";
    }

    @GetMapping("/song/add")
    public String addSong(Model model){
        Song song = new Song();
        model.addAttribute("song", song);
        return "song/add";
    }

    @PostMapping("/song/add")
    public String songAdd(@ModelAttribute("song") Song song){
        sRp.save(song);
        return "redirect:/song";
    }

    @GetMapping("/song/update/{id}")
    public String updateSong(@PathVariable int id, Model model){
        Song song = sRp.findById(id).orElse(null);
        model.addAttribute("song", song);
        return "song/update";
    }

    @PostMapping("/song/update")
    public String songUpdate(@ModelAttribute("song") Song song){
        sRp.save(song);
        return "redirect:/song";
    }

    @PostMapping("/song/delete/{id}")
    public String songDelete(@PathVariable int id){
        sRp.deleteById(id);
        return "redirect:/song";
    }
}
