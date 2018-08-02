package org.aikidodanang.controller;

import org.aikidodanang.model.Album;
import org.aikidodanang.model.Post;
import org.aikidodanang.repository.AlbumRepository;
import org.aikidodanang.repository.PostRepository;
import org.aikidodanang.utils.CloudinaryUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.parboiled.common.FileUtils;
import org.pegdown.PegDownProcessor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("edit")
@PreAuthorize("hasRole('ADMIN')")
public class EditorController {
    private final PegDownProcessor pegDownProcessor;
    private final PostRepository postRepository;
    private final CloudinaryUtil cloudinaryUtil;
    private final AlbumRepository albumRepository;

    public EditorController(PegDownProcessor pegDownProcessor,
                            PostRepository postRepository,
                            CloudinaryUtil cloudinaryUtil,
                            AlbumRepository albumRepository) {
        this.pegDownProcessor = pegDownProcessor;
        this.postRepository = postRepository;
        this.cloudinaryUtil = cloudinaryUtil;
        this.albumRepository = albumRepository;
    }

    @GetMapping("/post")
    public String redirectEditor() {
        return "editPost";
    }

    @PostMapping("/post")
    public String getContent(@RequestParam(value = "heading") String heading,
                             @RequestParam(value = "subheading") String subheading,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "process") String process,
                             @RequestParam(value = "article") String article,
                             Model model) {
        String realContent = pegDownProcessor.markdownToHtml(content)
                .replaceAll("<table>", "<table class='table'>");

        String title = Normalizer.normalize(heading, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        title = title.replaceAll("đ", "d").replaceAll("Đ", "D");
        title = title.toLowerCase();
        title = title.replaceAll("\\s+", "-");

        Post savePost = Post.builder()
                .article(!article.equals("no"))
                .content(realContent)
                .heading(heading)
                .subHeading(subheading)
                .title(title)
                .userName("Admin")
                .createDate(LocalDateTime.now())
                .build();

        if (process.equals("Save")) {
            postRepository.save(savePost);
            model.addAttribute("heading", "");
            model.addAttribute("subheading", "");
            model.addAttribute("content", "");
            model.addAttribute("result", savePost.getId() != null &&
                    !savePost.getId().isEmpty() ? "sucess" : "failed");
        } else {
            model.addAttribute("previewContent", realContent);
            model.addAttribute("heading", heading);
            model.addAttribute("subheading", subheading);
            model.addAttribute("content", content);
            model.addAttribute("result", "");
        }

        return "editPost";
    }

    @GetMapping("/album")
    public String redirectEditAlbum(@RequestParam("action") String action, Model model) {
        model.addAttribute("action", action);
        return "editAlbum";
    }

    @PostMapping("/album")
    public String createAlbum(@RequestParam("name") String name,
                              @RequestParam("images") MultipartFile[] images,
                              Model model) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile image : images) {
            File imageFile = new File(RandomStringUtils.random(10));
            try {
                imageFile.createNewFile();
                FileUtils.writeAllBytes(image.getBytes(), imageFile);
                image.transferTo(imageFile);
                urls.add(cloudinaryUtil.uploadImage(imageFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                imageFile.delete();
            }
        }

        Album album = Album.builder().name(name).urls(urls).build();
        albumRepository.save(album);

        if (album.getId() != null && !album.getId().isEmpty()) {
            model.addAttribute("result", "Success");
        } else {
            model.addAttribute("result", "Failed!!! Try again");
        }

        return "editAlbum";
    }
}
