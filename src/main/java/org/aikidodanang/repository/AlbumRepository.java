package org.aikidodanang.repository;

import org.aikidodanang.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    Album findAlbumByName(String albumName);
}
