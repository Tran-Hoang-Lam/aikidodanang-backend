package org.aikidodanang.service;

import org.aikidodanang.model.Album;
import org.aikidodanang.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll().stream().sorted((album1, album2) -> {
            if (album1.getCreateDate().compareTo(album2.getCreateDate()) > 0) {
                return -1;
            } else if (album1.getCreateDate().compareTo(album2.getCreateDate()) < 0) {
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }
}
