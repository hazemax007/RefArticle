package com.example.codeArticle.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.codeArticle.Interfaces.CategoryInterface;
import com.example.codeArticle.Interfaces.ImageInterface;
import com.example.codeArticle.Models.Category;
import com.example.codeArticle.Models.Image;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryInterface categoryInterface;


    @Autowired
	ImageInterface imageInterface;

    @GetMapping("/getAllCategories")
    @ResponseBody
    public List<Category> getAllCategories() {
        return categoryInterface.getAllCategories();
    }

    @GetMapping("/getCategory/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryInterface.getCategory(categoryId);
    }

    
    @PostMapping(value = {"/addCategory"} , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public Category saveCategory(@ModelAttribute Category category , @RequestPart MultipartFile[] image) {
        try {
				Set<Image> images = imageInterface.uploadImage(image);
				category.setCategoryImages(images);
				return categoryInterface.addCategory(category);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
    }


    @PutMapping("/updateCategory/{categoryId}")
    public Category updateCategory(@ModelAttribute Category category , @PathVariable("categoryId") Long categoryId , @RequestPart MultipartFile file) {
        return categoryInterface.updateCategory(category, categoryId , file);
    }


    @DeleteMapping("/removeCategory/{categoryId}")
    public void removeCategory(@PathVariable("categoryId") Long categoryId) {
        categoryInterface.deleteCategory(categoryId);
    }

    @PutMapping("/addQuestionsToCategory/{categoryId}/{questionId}")
    public Category addQuestionsToCategory(
            @PathVariable Long categoryId,
            @PathVariable("questionId") Long questionId) {
           return categoryInterface.addQuestionsToCategory(categoryId, questionId);
            
    }

   
}
