package com.example.codeArticle.Interfaces;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.codeArticle.Models.Response;

public interface ReferenceInerface {
    public String generateReferenceForUser(String username);
    public String generateReference(ArrayList<String> answers);
}
