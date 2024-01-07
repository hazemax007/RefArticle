package com.example.codeArticle.Controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeArticle.Interfaces.QuestionInterface;
import com.example.codeArticle.Models.Question;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    
    @Autowired
    private QuestionInterface questionInterface;


    @GetMapping("/getAllQuestions")
    @ResponseBody
    public LinkedList<Question> getAllQuestions() {
        return questionInterface.getAllQuestions();
    }

    @GetMapping("/getQuestion/{questionId}")
    @ResponseBody
    public Question getQuestion(@PathVariable("questionId") Long questionId){
        return questionInterface.getQuestion(questionId);
    }

    
    @PostMapping("/addQuestion")
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question) { 
		return questionInterface.addQuestion(question);	
    }


    @PutMapping("/updateQuestion/{questionId}")
    @ResponseBody
    public Question updateQuestion(@ModelAttribute Question question , @PathVariable("questionId") Long questionId) {
        return questionInterface.updateQuestion(question, questionId);
    }


    @DeleteMapping("/removeQuestion/{questionId}")
    public void removeQuestion(@PathVariable("questionId") Long questionId) {
        questionInterface.deleteQuestion(questionId);
    }

    @PatchMapping("/addAnswerToQuestion/{questionId}")
    public ResponseEntity<Question> addAnswerToQuestion(
            @PathVariable("questionId") Long questionId,
            @RequestBody String answerText) {

        Question updatedQuestion = questionInterface.addAnswerToQuestion(questionId, answerText);

        if (updatedQuestion != null) {
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
