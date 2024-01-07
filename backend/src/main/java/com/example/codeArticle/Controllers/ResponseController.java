package com.example.codeArticle.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeArticle.Interfaces.ResponseInterface;
import com.example.codeArticle.Models.Response;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController {
    
    @Autowired
    private ResponseInterface responseInterface;

    @GetMapping("/getAllResponses")
    public List<Response> getResponses() {
        return responseInterface.getAllResponses();
    }

    @GetMapping("/getResponse/{responseId}")
    public Response getResponse(@PathVariable("responseId") Long responseId){
        return responseInterface.getResponse(responseId);
    }
    
    @GetMapping("/getResponseByUserAndQuestion/{username}/{questionId}")
    public Response getResponsesByUserAndQuestion(@PathVariable("username") String username,@PathVariable("questionId") Long questionId){
        return responseInterface.getResponseByUserAndQuestion(username, questionId);
    }

    @PostMapping("/addOrUpdateResponseToQuestion/{username}/{questionId}")
    public Response addOrUpdateResponseToQuestion(
        @PathVariable("username") String username,
        @PathVariable("questionId") Long questionId,
        @RequestBody Response response) {
        return responseInterface.addOrUpdateAnswer(username, questionId, response);
        }

    /*@PostMapping("/addResponseToQuestion")
    public ResponseEntity<String> addAnswerToQuestion(
            @PathVariable Long userId,
            @PathVariable Long questionId,
            @RequestBody Response response) {
        // Fetch user and question from their respective services
        User user = userService.getUserById(userId);
        Question question = questionService.getQuestionById(questionId);

        if (user != null && question != null) {
            // Add answer to the question by the user
            Answer addedAnswer = answerService.addAnswerToQuestion(user, question, answerText);
            return ResponseEntity.ok("Answer added with ID: " + addedAnswer.getId());
        } else {
            return ResponseEntity.badRequest().body("Invalid user or question ID");
        }
    }*/
}
