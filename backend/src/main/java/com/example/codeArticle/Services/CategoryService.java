package com.example.codeArticle.Services;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.codeArticle.Interfaces.CategoryInterface;
import com.example.codeArticle.Models.Category;
import com.example.codeArticle.Models.Question;
import com.example.codeArticle.Repositories.CategoryRepo;
import com.example.codeArticle.Repositories.QuestionRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService implements CategoryInterface{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private QuestionRepo questionRepo;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(null);
    }

    @Override
    public Category addCategory(Category c) { 
        return categoryRepo.save(c);
       
    }

    @Override
    public Category updateCategory(Category c, Long categoryId , MultipartFile file) {
        Category currentCat = categoryRepo.findById(categoryId).orElseThrow(null);
        currentCat.setName(c.getName());
        currentCat.setDescription(c.getDescription());

        return categoryRepo.save(currentCat); // Save the updated product
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public Category addQuestionsToCategory(Long categoryId, Long questionId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(null);
        Question question = questionRepo.findById(questionId).orElseThrow(null);
        category.getQuestions().add(question);
        categoryRepo.save(category);
        return category;
    }

   


    
}
