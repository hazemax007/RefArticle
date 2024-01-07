package com.example.codeArticle.Interfaces;

import java.io.IOException;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.codeArticle.Models.Image;

public interface ImageInterface {
    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException;
}
