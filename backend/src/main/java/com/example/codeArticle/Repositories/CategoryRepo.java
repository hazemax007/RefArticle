package com.example.codeArticle.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.codeArticle.Models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

    
}
