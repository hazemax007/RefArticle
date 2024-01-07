package com.example.codeArticle.Controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codeArticle.Interfaces.ReferenceInerface;
import com.example.codeArticle.Models.Response;

@RestController
@RequestMapping("/api/v1/reference")
public class ReferenceController {

    @Autowired
    private ReferenceInerface referenceInerface;
    
    @GetMapping("/getReference/{username}")
    public String generateReferenceForUser(@PathVariable("username") String username) {
        return referenceInerface.generateReferenceForUser(username);
    }
}
