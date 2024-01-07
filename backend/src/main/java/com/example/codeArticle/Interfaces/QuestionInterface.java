package com.example.codeArticle.Interfaces;

import java.util.LinkedList;
import java.util.List;

import com.example.codeArticle.Models.Question;

public interface QuestionInterface {
    LinkedList<Question>getAllQuestions();
    Question getQuestion(Long questionId);
    Question addQuestion(Question q);
    Question updateQuestion(Question q , Long questionId);
    void deleteQuestion(Long questionId);
    public Question addAnswerToQuestion(Long questionId, String answerText);
}
