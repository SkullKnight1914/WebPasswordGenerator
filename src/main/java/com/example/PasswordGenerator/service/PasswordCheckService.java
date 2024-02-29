package com.example.PasswordGenerator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordCheckService {

    public List<String> checkingPassword(List<String> generatedPasswords) {
        List<String> passwordsInfo = new ArrayList<>();

        for (int i = 0; i < generatedPasswords.size(); i++) {
            passwordsInfo.add(checkingForString(generatedPasswords.get(i)));
        }

        return passwordsInfo;
    }

    public String checkingForString(String password) {
        int charsSetSize = getCharactersSetSize(password);
        long combinations = calculateTotalCombinations(password.length(), charsSetSize);
        return "Number of combinations for this password: " + combinations + ".";
    }

    private int getCharactersSetSize(String password) {
        int charsSetSize = 0;
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        boolean containsDigit = false;
        boolean containsSpecialChars = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                containsLowerCase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else {
                containsSpecialChars = true;
            }
        }

        if (containsLowerCase) {
            charsSetSize += 26;
        }
        if (containsUpperCase) {
            charsSetSize += 26;
        }
        if (containsDigit) {
            charsSetSize += 10;
        }
        if (containsSpecialChars) {
            charsSetSize += 32;
        }

        return charsSetSize;
    }

    private long calculateTotalCombinations(int length, int charsSetSize) {
        long totalCombinations = (long) Math.pow(charsSetSize, length);
        return totalCombinations;
    }
}
