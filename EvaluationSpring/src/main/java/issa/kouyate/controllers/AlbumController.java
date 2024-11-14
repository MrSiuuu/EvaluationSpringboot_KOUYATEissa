package issa.kouyate.controllers;

import issa.kouyate.entities.Album;
import issa.kouyate.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public String listAlbums(Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());
        return "album/list"; // Thymeleaf pour afficher la liste des albums
    }

    @GetMapping("/add")
    public String addAlbumForm(Model model) {
        model.addAttribute("album", new Album());
        return "album/add"; //pour ajouter un album
    }

    @PostMapping
    public String saveAlbum(@ModelAttribute Album album) {
        albumService.saveAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping("/edit/{id}")
    public String editAlbumForm(@PathVariable Long id, Model model) {
        Optional<Album> album = albumService.getAlbumById(id);
        if (album.isPresent()) {
            model.addAttribute("album", album.get());
            return "album/edit"; //  pour modifier un album
        } else {
            return "redirect:/albums";
        }
    }

    @PostMapping("/update")
    public String updateAlbum(@ModelAttribute Album album) {
        albumService.saveAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return "redirect:/albums";
    }
}
