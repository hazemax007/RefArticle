package com.example.codeArticle.Interfaces;


import java.util.List;

import com.example.codeArticle.Models.Response;

public interface ResponseInterface {
    //public Response addAnswerToQuestion(Long userId, Long questionId, Response response);
    public Response addOrUpdateAnswer(String username, Long questionId, Response response);
    public Response getResponseByUserAndQuestion(String username, Long questionId);
    public List<Response> getAllResponses();
    public Response getResponse(Long responseId);
}
