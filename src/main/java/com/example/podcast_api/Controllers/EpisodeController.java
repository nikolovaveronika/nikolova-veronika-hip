package com.example.podcast_api.Controllers;

import com.example.podcast_api.DTOs.EpisodeRequest;
import com.example.podcast_api.Entities.Episode;
import com.example.podcast_api.Services.EpisodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
@SuppressWarnings("unused")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    // POST /episodes — register a new episode
    @PostMapping
    public ResponseEntity<Episode> createEpisode(@Valid @RequestBody EpisodeRequest request) {

        Episode episode = new Episode();
        episode.setTitle(request.getTitle());
        episode.setDescription(request.getDescription());
        episode.setAuthor(request.getAuthor());

        return new ResponseEntity<>(episodeService.createEpisode(episode), HttpStatus.CREATED);
    }


    // GET /episodes — list all episodes
    @GetMapping
    public ResponseEntity<List<Episode>> getAllEpisodes() {
        return ResponseEntity.ok(episodeService.getAllEpisodes());
    }

    // GET /episodes/{id} — get one episode
    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(episodeService.getEpisodeById(id));
    }

    // PUT /episodes/{id} — update episode metadata
    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable Long id, @Valid @RequestBody Episode episode) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, episode));
    }

    // DELETE /episodes/{id} — hard delete episode and all segments
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Long id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.noContent().build();
    }
}