package com.example.PasswordGenerator.controller;

import com.example.PasswordGenerator.service.PasswordGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.PasswordGenerator.model.Password;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final PasswordGeneratorService service;

    @GetMapping("/")
    public String mainpage() {
        return "main";
    }

    /*
            используем Model, чтобы получить доступ к модели, которая предоставляет механизм передачи данных между контроллером
        и представлением.
            используем addAttribute для добавления атрибутов в модель. Атрибуты это данные которые мы передаем из контроллера
        в представление.
     */
    @GetMapping("/passwordform")
    public String getPasswordForm(Model model) {
        model.addAttribute("password", new Password());
        return "passwordForm";
    }

    /*
        используем @ModelAttribute("password") для привязки данных отправленные юзером, к объекту Password
     */
    @PostMapping("/passwordform/generate")
    public String generatePassword(@ModelAttribute("password") Password password, Model model) {
        List<String> generatedPasswords = service.generatePasswords(password);
        model.addAttribute("passwords", generatedPasswords);
        return "passwordResult";
    }

    @GetMapping("/checking")
    public String checkingPage() {
        return "checking";
    }
}
