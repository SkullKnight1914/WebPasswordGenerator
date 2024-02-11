package com.example.PasswordGenerator.service;

import com.example.PasswordGenerator.model.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Service
public class PasswordGeneratorService {
    private List generatedPasswords;
    private char[] symbols;

    public List<String> generatePasswords(Password password) {
        generatedPasswords = new ArrayList<>(password.getCount());
        initializeSymbols(password);
        Random random = new Random();
        for (int i = 0; i < password.getCount(); i++) {
            generatedPasswords.add(generate(password.getLength(), random));
        }
        return generatedPasswords;
    }

    private void initializeSymbols(Password password) {
        StringBuilder sb = new StringBuilder();
        sb.append("abcdefghijklmnopqrstuvwxyz");
        if (password.isIncludeNumbers()) {
            sb.append("1234567890");
        }
        if (password.isIncludeUpperCase()) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (password.isIncludeSpecialChars()) {
            sb.append("!@#$%^&*()`~[]{}-_+=|/.,:;?");
        }
        symbols = sb.toString().toCharArray();
    }

    private String generate(int length, Random random) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(symbols.length);
            char randomChar = symbols[randomIndex];
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
