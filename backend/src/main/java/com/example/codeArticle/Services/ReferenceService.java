package com.example.codeArticle.Services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.codeArticle.Interfaces.ReferenceInerface;
import com.example.codeArticle.Models.Response;
import com.example.codeArticle.Models.User;
import com.example.codeArticle.Repositories.UserRepo;

@Service
public class ReferenceService implements ReferenceInerface{

    @Autowired
    private UserRepo userRepo;

    @Override
    public String generateReferenceForUser(String username) {
        // Filtrer les réponses de l'utilisateur spécifié
        User user = userRepo.findByUsername(username).orElseThrow(null);
        ArrayList<String> specificUserAnswers = (ArrayList<String>) user.getAnswers().stream()
                                        .filter(answer -> answer.getUser().getUsername().equals(username))
                                        .map(Response::getContent)
                                        .collect(Collectors.toList());

        // Générer la référence à partir des réponses de l'utilisateur spécifié
        return generateReference(specificUserAnswers);
    }
    
    @Override
    public String generateReference(ArrayList<String> answers) {
        StringBuilder reference = new StringBuilder();

        for (String answer : answers) {
            reference.append(answer);     
        }

        return reference.toString();
    }

    /*private boolean isString(String str) {
        // Vérifier si la réponse est une chaîne de caractères
        try {
            Integer.parseInt(str); // Tente de convertir la chaîne en un nombre
            return false; // Si réussi, la réponse est un nombre
        } catch (NumberFormatException e) {
            return true; // Si échoue, la réponse est une chaîne de caractères
        }
    }*/

    

    
}
