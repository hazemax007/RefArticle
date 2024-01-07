package com.example.codeArticle.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.codeArticle.Models.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long>{
    
}
