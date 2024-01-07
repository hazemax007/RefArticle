package com.example.codeArticle.Services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.codeArticle.Interfaces.ImageInterface;
import com.example.codeArticle.Models.Image;

@Service
public class ImageService implements ImageInterface{

    @Override
	public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException{
		Set<Image> images = new HashSet<>();
		for(MultipartFile file:multipartFiles) {
			Image image = new Image(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes()
					);
			images.add(image);
		}
		return images;
	}
    
}
