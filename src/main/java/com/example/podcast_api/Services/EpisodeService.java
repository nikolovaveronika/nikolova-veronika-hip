package com.example.podcast_api.Services;

import com.example.podcast_api.Entities.Episode;
import com.example.podcast_api.Exception.ResourceNotFoundException;
import com.example.podcast_api.Repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    public Episode getEpisodeById(Long id) {
        return episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode not found with id: " + id));
    }

    public Episode createEpisode(Episode episode) {
        return episodeRepository.save(episode);
    }

    public Episode updateEpisode(Long id, Episode updated) {
        Episode episode = getEpisodeById(id);
        episode.setTitle(updated.getTitle());
        episode.setDescription(updated.getDescription());
        episode.setAuthor(updated.getAuthor());
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Long id) {
        getEpisodeById(id); // throws 404 if not found
        episodeRepository.deleteById(id);
    }
}