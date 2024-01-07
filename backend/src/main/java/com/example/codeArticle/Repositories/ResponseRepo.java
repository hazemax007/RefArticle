package com.example.codeArticle.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.codeArticle.Models.Question;
import com.example.codeArticle.Models.Response;
import com.example.codeArticle.Models.User;

@Repository
public interface ResponseRepo extends JpaRepository<Response,Long>{
    List<Response> findByUserAndQuestion(User user, Question question);
}
