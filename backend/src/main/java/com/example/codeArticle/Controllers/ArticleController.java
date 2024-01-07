package com.example.codeArticle.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/article")
public class ArticleController {
    
    @GetMapping("/test")
    @PreAuthorize("isAuthenticated()")
    public String getMethodName() {
        return "testing authorization service";
    }
    
}
