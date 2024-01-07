package com.example.codeArticle.Services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.codeArticle.Interfaces.QuestionInterface;
import com.example.codeArticle.Models.Category;
import com.example.codeArticle.Models.Question;
import com.example.codeArticle.Models.Response;
import com.example.codeArticle.Repositories.CategoryRepo;
import com.example.codeArticle.Repositories.QuestionRepo;


@Service
public class QuestionService implements QuestionInterface{
    
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public LinkedList<Question> getAllQuestions() {
        return (LinkedList<Question>) questionRepo.findAll();
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepo.findById(questionId).orElseThrow(null);
    }

    public Question addQuestion(Question q) {
        return questionRepo.save(q);
    }

    @Override
    public Question updateQuestion(Question q, Long questionId) {
        Question question = questionRepo.findById(questionId).orElseThrow(null);
        question.setContent(q.getContent());
        question.setCategories(q.getCategories());
        return questionRepo.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepo.deleteById(questionId);
    }

    @Override
    public Question addAnswerToQuestion(Long questionId, String answerText) {
        Optional<Question> optionalQuestion = questionRepo.findById(questionId);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            Response answer = new Response();
            answer.setContent(answerText);
            question.getAnswers().add(answer);
            return questionRepo.save(question);
        } else {
            // Handle if question with the provided ID doesn't exist
            // Possibly throw an exception or return null/error response
            return null;
        }
    }



    

}
