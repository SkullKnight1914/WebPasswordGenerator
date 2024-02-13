package com.example.PasswordGenerator.controller;

import com.example.PasswordGenerator.service.PasswordCheckService;
import com.example.PasswordGenerator.service.PasswordGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.PasswordGenerator.model.Password;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final PasswordGeneratorService generatorService;
    private final PasswordCheckService checkService;

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
        List<String> generatedPasswords = generatorService.generatePasswords(password);
        List<String> generatedPasswordsInfo = checkService.checkingPassword(generatedPasswords);
//        model.addAttribute("passwords", generatedPasswords);
        LinkedHashMap<String, String> output = new LinkedHashMap<>();

        for (int i = 0; i < generatedPasswords.size(); i++) {
            output.put(generatedPasswords.get(i), generatedPasswordsInfo.get(i));
        }
        model.addAttribute("passwords", output);
        return "passwordResult";
    }

    @GetMapping("/checking")
    public String checkingPage() {
        return "checking";
    }
}
