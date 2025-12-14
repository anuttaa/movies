package com.example.movies.controllers;

import com.example.movies.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/uploads")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagesController {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @GetMapping("/*/**")
    public ResponseEntity<Resource> serveFile(HttpServletRequest request) {
        String filepath = request.getRequestURI().substring("/uploads/".length());
        Path file = Paths.get("uploads").resolve(filepath);
        logger.info("Trying 🪐🌍🌋 to serve file: {}", file.toString());
        if (!Files.exists(file)) {
           logger.error("File 🚨🚩🚨 not found: {} ", file.toString());
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(file);
        logger.info("Avatar Image fetch ⚡🌙⚡⚡: {}", resource.getFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
