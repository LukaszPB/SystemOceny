package com.example.projektgruptest.controller;

import com.example.projektgruptest.repo.PracownikRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @Autowired
    PracownikRepo pracownikRepo;

    @GetMapping("/")
    public Object home(Model model, HttpSession httpSession) {
        if (pracownikRepo != null) {
            model.addAttribute("pracownicy", pracownikRepo.findAll());
        }
        return pracownikRepo.findAll();
    }


}
