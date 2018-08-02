package org.aikidodanang.controller;

import org.aikidodanang.model.Post;
import org.aikidodanang.repository.PostRepository;
import org.pegdown.PegDownProcessor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("edit")
@PreAuthorize("hasRole('ADMIN')")
public class EditorController {
    private final PegDownProcessor pegDownProcessor;
    private final PostRepository postRepository;
    private final MessageChannel createAlbumChannel;

    public EditorController(PegDownProcessor pegDownProcessor,
                            PostRepository postRepository,
                            MessageChannel createAlbumChannel) {
        this.pegDownProcessor = pegDownProcessor;
        this.postRepository = postRepository;
        this.createAlbumChannel = createAlbumChannel;
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
        Map<String, Object> data = new HashMap<>();
        data.put("albumName", name);
        data.put("albumImages", images);

        createAlbumChannel.send(MessageBuilder.withPayload(data).build());

        model.addAttribute("result", "Creating your album!!! Check album screen after 5 minutes");

        return "redirect:/edit/album?action=add";
    }
}
