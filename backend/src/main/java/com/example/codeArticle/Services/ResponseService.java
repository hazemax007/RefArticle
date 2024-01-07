package com.example.codeArticle.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.codeArticle.Interfaces.ResponseInterface;
//import com.example.codeArticle.Interfaces.ResponseInterface;
import com.example.codeArticle.Models.Question;
import com.example.codeArticle.Models.Response;
import com.example.codeArticle.Models.User;
import com.example.codeArticle.Repositories.QuestionRepo;
import com.example.codeArticle.Repositories.ResponseRepo;
import com.example.codeArticle.Repositories.UserRepo;

@Service
public class ResponseService implements ResponseInterface {

    @Autowired
    private ResponseRepo responseRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public Response addOrUpdateAnswer(String username, Long questionId, Response response) {
        // Fetch user by username from the database (assuming username is unique)
        User user = userRepo.findByUsername(username).orElseThrow(null);
        
        // Fetch question by ID from the database
        Question question = questionRepo.findById(questionId).orElseThrow(null);

        if (user != null && question!= null) {

            List<Response> existingResponses = responseRepo.findByUserAndQuestion(user, question);

            if (!existingResponses.isEmpty()) {
                // Update existing answer
                Response existingResponse = existingResponses.get(0); // Assuming one answer per user per question
                existingResponse.setContent(response.getContent());
                return responseRepo.save(existingResponse);
            } else {
                // Create new answer
                Response newResponse = new Response();
                newResponse.setUser(user);
                newResponse.setQuestion(question);
                newResponse.setContent(response.getContent());
                question.getAnswers().add(newResponse);
                return responseRepo.save(newResponse);
            }
        } else {
            // Handle scenario if user or question is not found
            // You can throw an exception or handle it based on your application's requirements
            return null;
        }
    }

    @Override
    public Response getResponseByUserAndQuestion(String username, Long questionId){
        User user = userRepo.findByUsername(username).orElseThrow(null);
        Question question = questionRepo.findById(questionId).orElseThrow(null);
        if(user!=null && question!=null){
            return responseRepo.findByUserAndQuestion(user, question).stream().findFirst().orElse(null);
        }else{
            return null;
        }
    }

    @Override
    public Response getResponse(Long responseId) {
        return responseRepo.findById(responseId).orElseThrow(null);
    }

    @Override
    public List<Response> getAllResponses() {
        return responseRepo.findAll();
    }


    

    /*@Override
    public Response addAnswerToQuestion(Long userId, Long questionId, Response response) {
        User user = userRepo.findById(userId).orElseThrow(null);
        Question question = questionRepo.findById(questionId).orElseThrow(null);
        Response response2 = new Response();
        if (user != null && question != null) {
            response2.setContent(response.getContent());
            response2.setQuestion(question);
            response2.setUser(user);
            return responseRepo.save(response2);
        }
        return null;
    }*/

    
    
}
