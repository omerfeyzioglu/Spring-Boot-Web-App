package com.example.demo.controllers;

import com.example.demo.models.Artist;
import com.example.demo.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository aRp;

    @GetMapping("/artist")
    private String getArtists(Model model){
        Iterable<Artist> artists = aRp.findAll();
        model.addAttribute("artist", artists);
        return "artist/index" ;
    }

    @GetMapping("/artist/add")
    public String addArtist(Model model){
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        return "artist/add";
    }

    @PostMapping("/car/add")
    public String artistAdd(@ModelAttribute("artist") Artist artist){
        aRp.save(artist);
        return "redirect:/artist" ;

    }

    @GetMapping("/artist/update/{id}")
    public String updateArtist(@PathVariable int id, Model model){
        Artist artist = aRp.findById(id).get();
        model.addAttribute("artist", artist);
        return "artist/update" ;
    }

    @PostMapping("/artist/update")
    public String artistUpdate(@ModelAttribute("artist") Artist artist){
        aRp.save(artist);
        return "redirect:/artist";
    }

    @PostMapping("/car/delete/{id}")
    public String artistDelete(@PathVariable int id){
        aRp.deleteById(id);
        return "redirect:/artist" ;
    }


}
