package com.example.codeArticle.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.codeArticle.Models.Category;

public interface CategoryInterface {
    
    List<Category>getAllCategories();
    Category getCategory(Long categoryId);
    Category addCategory(Category c);
    Category updateCategory(Category c , Long categoryId , MultipartFile file);
    void deleteCategory(Long categoryId);
    Category addQuestionsToCategory(Long categoryId, Long questionId);
    
}
