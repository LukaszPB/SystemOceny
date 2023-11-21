package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.repo.OcenaRepo;
import com.example.projektgruptest.repo.PracownikRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    @Autowired
    PracownikRepo pracownikRepo;

    @Autowired
    OcenaRepo ocenaRepo;

    @GetMapping("/")
    public List<Ocena> home(Model model, HttpSession httpSession) {
        return ocenaRepo.findAll();
    }


}
