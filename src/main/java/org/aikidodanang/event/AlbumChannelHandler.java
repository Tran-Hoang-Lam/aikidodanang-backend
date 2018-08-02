package org.aikidodanang.event;

import lombok.extern.log4j.Log4j2;
import org.aikidodanang.model.Album;
import org.aikidodanang.repository.AlbumRepository;
import org.aikidodanang.utils.CloudinaryUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.parboiled.common.FileUtils;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MessageEndpoint
@Component
@Log4j2
public class AlbumChannelHandler {
    private final CloudinaryUtil cloudinaryUtil;
    private final AlbumRepository albumRepository;

    public AlbumChannelHandler(CloudinaryUtil cloudinaryUtil,
                               AlbumRepository albumRepository) {
        this.cloudinaryUtil = cloudinaryUtil;
        this.albumRepository = albumRepository;
    }

    @ServiceActivator(inputChannel = "createAlbumChannel")
    public void handle(Map<String, Object> data) {
        log.info("Start create album");
        String name = (String) data.get("albumName");
        MultipartFile[] images = (MultipartFile[]) data.get("albumImages");
        log.info("Album name " + name);
        log.info("Total image " + images.length);
        List<String> urls = new ArrayList<>();
        for (MultipartFile image : images) {
            File imageFile = new File(RandomStringUtils.random(10));
            try {
                imageFile.createNewFile();
                FileUtils.writeAllBytes(image.getBytes(), imageFile);
                image.transferTo(imageFile);
                String url = cloudinaryUtil.uploadImage(imageFile);
                log.info("Upload success: " + url);
                urls.add(url);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                imageFile.delete();
            }
        }

        Album album = Album.builder().name(name).urls(urls).build();
        albumRepository.save(album);
    }
}
